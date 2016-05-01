<?php

function query() {
	require "../../db/dbconn.php";
	$query = "select * from stock";
	$result = $mysqli->query($query);
	$result->data_seek(0);
	$list = array(); 
	while ($row = $result->fetch_assoc()) {
		$list[] = $row;
	}
	return $list;
}

function update($base,$id,$back,$front) {
	require "../../db/dbconn.php";
	$query = "UPDATE $base SET back='$back',front='$front' WHERE id='$id'";
	$result = $mysqli->query($query);
}

$data = query();
foreach($data as $key => $value)
{
	foreach($value as $key => $v) {
		$id = $value['id'];
		$front = $value['front'];
		$back = $value['back'];
		break;
	}
	$front = $front - 1;
	update("stock",$id,$back,$front);
}
