  <?php
      mysql_connect("localhost","root","");
      mysql_select_db("guide");
      $q=mysql_query("SELECT * FROM Batiment As b, Points_reference As p WHERE b.Id = p.Id_batiment And Type_point = 'centre'" );
      while($e=mysql_fetch_assoc($q))
              $output[]=$e;
      print(json_encode($output));
      mysql_close();
      ?>
