<?php 

require "../db/dbconn.php";

if(isset($_GET['type']) && $_GET['pass'] == "wordpass") {

	/* "im trying atlest" */
	$id = mysql_escape_string($_GET['id']);
	$type = mysql_escape_string($_GET['type']);
	$name = mysql_escape_string($_GET['name']);
	$back = mysql_escape_string($_GET['back']);
	$front = mysql_escape_string($_GET['front']);
	$location = mysql_escape_string($_GET['location']);
	$amount = mysql_escape_string($_GET['amount']);

	if(isset($_GET['base'])) {
		$base = mysql_escape_string($_GET['base']);
	} else {
		$base = "stock";
	}


	if($_GET['type'] == "refresh") {
		$Jam = new Jam();
		$Jam->query($base);
	}
	if($_GET['type'] == "del") {
		$Jam = new Jam();
		$Jam->del($base,$id);
	}
	if($_GET['type'] == "update") {
		$Jam = new Jam();
		$Jam->update($base,$id,$back,$front);
	}
	if($_GET['type'] == "insert") {
		$Jam = new Jam();
		$Jam->insert($base,$name,$back,$location);
	}


	/* for q */
	if($_GET['type'] == "restock") {
		$Jam = new Jam();
		$Jam->restock();
	}
	if($_GET['type'] == "move") {
		$Jam = new Jam();
		$Jam->move($id);
	}

}

class Jam {
	/* table wide */
	function query($base) {
		require "../db/dbconn.php";
		$query = "select * from $base";
		$result = $mysqli->query($query);
		$result->data_seek(0);
		$list = array(); 
		while ($row = $result->fetch_assoc()) {
			$list[] = $row;
		}
		echo json_encode($list);
	}
	function del($base,$id){
		require "../db/dbconn.php";
		$query = "DELETE FROM $base WHERE id=$id";
		$result = $mysqli->query($query);
	}

	/* for the q */
	function push($id,$amount) {
		require "../db/dbconn.php";
		$query = "select * from stock where id=$id";
		$result = $mysqli->query($query);
		$result->data_seek(0);
		while ($row = $result->fetch_assoc()) {
			$name = $row['name'];
			$location = $row['location'];
			$back = $row['back'];
			$front = $row['front'];
		}
		$query = "INSERT INTO q(stockid,name,location,amount) VALUES ('$id','$name','$location','$amount')";
		$result = $mysqli->query($query);
		$this->query('q');

	}

	function restock(){
		require "../db/dbconn.php";

		//Checking Front Stock
		$query = "select * from stock";
		$result = $mysqli->query($query);
		$result->data_seek(0);
		$list = array();
		while ($row = $result->fetch_assoc()) {
			$list[] = $row;
		}
		foreach($list as $a) {
				$front = $a['front'];
				$back = $a['back'];
				$id = $a['id'];
			if($front < 5){
				$amount = 5 - $front;
				if($amount < $back) {
					$this->push($id,$amount);
				} else {
				}
			}
		}
	}

	function move($id) {
		require "../db/dbconn.php";

		//stocking back
		$query = "select * from q where id=$id";
		$result = $mysqli->query($query);
		$result->data_seek(0);
		while ($row = $result->fetch_assoc()) {
			$sid = $row['stockid'];
			$amount = $row['amount'];
		}

		$this->del("q",$id);
		$query = "select * from stock where id=$sid";
		$result = $mysqli->query($query);
		$result->data_seek(0);
		while ($row = $result->fetch_assoc()) {
			$id = $row['stockid'];
			$back = $row['back'];
			$front = $row['front'];

		}
		if($back < $amount){
			echo "ERROR: That amount can not be found in stock";
			die();
		}
		$back = $back - $amount;
		$front = $front + $amount;
		$this->update("stock",$sid,$back,$front);
	}

	/* for the stock */
	function insert($base,$name,$back,$location) {
		require "../db/dbconn.php";
		$query = "INSERT INTO $base(name,back,location) VALUES ('$name','$back','$location')";
		$result = $mysqli->query($query);
		$this->query($base);
	}
	function update($base,$id,$back,$front) {
		require "../db/dbconn.php";
		$query = "UPDATE $base SET back='$back',front='$front' WHERE id='$id'";
		$result = $mysqli->query($query);
		$this->query($base);
	}

}
/* Stoping Double Sending */
header("Content-Type: application/json");

