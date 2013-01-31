      <?php
      mysql_connect("localhost","root","");
      mysql_select_db("guide");
	$val=$_REQUEST['Id_batiment'];
	$int=(int)$val;
      $q=mysql_query("SELECT * FROM Batiment As b, Points_reference As p WHERE b.Id='".$int."' And b.Id = p.Id_batiment And p.Type_point='centre'");
      while($e=mysql_fetch_assoc($q))
              $output[]=$e;
      print(json_encode($output));
      mysql_close();
      ?>
