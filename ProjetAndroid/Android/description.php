  <?php
      mysql_connect("localhost","root","");
      mysql_select_db("guide");
      $lon=null;
      $lat=null;
	if (!empty($_REQUEST['lon']))$lon=$_REQUEST['lon'];
	if (!empty($_REQUEST['lat']))$lat=$_REQUEST['lat'];
      $q=mysql_query("SELECT * FROM Batiment As b, Points_reference As p, Salle As s WHERE b.Id = p.Id_batiment And Type_point = 'centre' and b.Id = s.Id_Batiment And Latitude = '".$lat."' And Longitude = '".$lon."'" );
      while($e=mysql_fetch_assoc($q))
              $output[]=$e;
      print(json_encode($output));
      
      mysql_close();
  ?>
