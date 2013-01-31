  <?php
      mysql_connect("localhost","root","");
      mysql_select_db("guide");
      $val=null;
      $salle=null;
       if (!empty($_REQUEST['nom'])) $val=$_REQUEST['nom'];
       if (!empty($_REQUEST['salle'])) $salle=$_REQUEST['salle'];

      $q=mysql_query("SELECT * FROM Batiment As b, Points_reference As p WHERE b.Id = p.Id_batiment And Type_point = 'centre'");

	$q1=mysql_query("SELECT * FROM Batiment As b, Points_reference As p WHERE b.Id = p.Id_batiment And Type_point = 'centre' And b.nom='".$val."'");
	$q2=mysql_query("SELECT * FROM Batiment As b, Points_reference As p, Salle As s WHERE b.Id = p.Id_batiment And Type_point = 'centre' And b.Id = s.Id_batiment And s.nom='".$salle."'");

	if ($val != null)
	      while($e=mysql_fetch_assoc($q1))
	              $output[]=$e;
	else if ($salle != null)
	      while($e=mysql_fetch_assoc($q2))
	              $output[]=$e;
	else
	      while($e=mysql_fetch_assoc($q))
	              $output[]=$e;
      print(json_encode($output));

      
      mysql_close();
      ?>
