      <?php
      mysql_connect("localhost","root","");
      mysql_select_db("guide");
	//$val=$_REQUEST['Id_batiment'];
	$int=3;
      	$q=mysql_query("SELECT * FROM Points_reference WHERE Type_point = 'lateral' and Id_Batiment='".$int."'");
      while($e=mysql_fetch_assoc($q))
              $output[]=$e;

print(json_encode($output));
      mysql_close();

	
      ?>

