  <?php
      mysql_connect("localhost","root","");
      mysql_select_db("Guide");
	
      	$q=mysql_query("SELECT * FROM Historique ");
      while($e=mysql_fetch_assoc($q))
              $output[]=$e;

print(json_encode($output));
      mysql_close();

	
      ?>
