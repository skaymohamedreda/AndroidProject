  <?php
      mysql_connect("localhost","root","");
      mysql_select_db("Guide");
	$nom=$_REQUEST['a'];

	echo $nom; 
        $q1=mysql_query(" INSERT INTO `Guide`.`Historique` (`nom`) VALUES ('".$nom."')");

	      while($e=mysql_fetch_assoc($q1))
	              $output[]=$e;


      
      mysql_close();
      ?>
