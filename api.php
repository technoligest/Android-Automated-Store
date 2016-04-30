<?php 

require "../db/dbconn.php";

if(isset($_GET['type']) && $_GET['pass'] == "wordpass") {
	/*
	$id = $_GET['id'];
	$type = $_GET['type'];
	$name = $_GET['name'];
	$back = $_GET['back'];
	$front = $_GET['front'];
	 */

	if($_GET['type'] == "refresh") {
		$Jam = new Jam();
		$Jam->query();
	}
	
	if($_GET['type'] == "update") {
		$Jam = new Jam();
		$Jam->update($_GET['id'],$_GET['back'],$_GET['front']);
	}

	if($_GET['type'] == "insert") {
		$Jam = new Jam();
		$Jam->push($_GET['name'],$_GET['back'],$_GET['front']);
	}

}

class Jam {

	function push($name,$back,$front) {
		require "../db/dbconn.php";
		$query = "INSERT INTO stock(name,back,front) VALUES ('$name','$back','$front')";
		$result = $mysqli->query($query);
		$this->query();
	}

	function query() {
		require "../db/dbconn.php";
		$query = "select * from stock";
		$result = $mysqli->query($query);
		$result->data_seek(0);
		$list = array(); 
		while ($row = $result->fetch_assoc()) {
			$list[] = $row;
		}
		echo json_encode($list);
	}

	function update($id,$back,$front) {
		require "../db/dbconn.php";
		$query = "UPDATE stock SET back='$back',front='$front' WHERE id='$id'";
		$result = $mysqli->query($query);
		$this->query();
	}

}
/* Stoping Double Sending */
header("HTTP/1.1 200 OK");

