-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: esquema_proyecto_2.0
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividades`
--

DROP TABLE IF EXISTS `actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividades` (
  `idActividad` int(11) NOT NULL,
  `codigoSubtipo` varchar(4) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` text,
  `url` varchar(200) NOT NULL,
  `Imagen` blob,
  PRIMARY KEY (`idActividad`),
  KEY `fk_Actividades_subtipo1_idx` (`codigoSubtipo`),
  CONSTRAINT `fk_Actividades_subtipo1` FOREIGN KEY (`codigoSubtipo`) REFERENCES `subtipo` (`codigoSubtipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividades`
--

LOCK TABLES `actividades` WRITE;
/*!40000 ALTER TABLE `actividades` DISABLE KEYS */;
INSERT INTO `actividades` VALUES (1,'1','Iglesia San Agustín',NULL,'https://sanagustinvalencia.com/',NULL),(2,'1','Micalet','El Miguelete es el campanario de la Catedral de Valencia. Su construcción se inició en 1381 y terminó en 1429.\n\nTiene en total 70 metros de altura con 207 escalones. Es uno de los monumentos más visitados de la ciudad.\nSe puede visitar todos los días del año adquiriendo la entrada.\n\n \nEn El Miguelete hay tres salas: la prisión, la del campanero y la de las campanas','http://www.valencia.es/ayuntamiento/Infociudad_accesible.nsf/vDocumentosWebListado/8D452C1CCBCAD1D2C12572C20023DA32?OpenDocument',NULL),(3,'1','Catedral de Valencia',NULL,'http://www.catedraldevalencia.es/',NULL),(4,'2','Ciudad de las Artes y las Ciencias',NULL,'https://www.cac.es/es/home.html',NULL),(5,'2','Oceanográfico',NULL,'https://www.oceanografic.org/',NULL),(6,'3','Playas El Saler',NULL,'https://www.visitvalencia.com/que-hacer-valencia/naturaleza-en-valencia/el-saler',NULL),(7,'3','Club Náutico',NULL,'http://www.rcnv.es/',NULL),(8,'4','Museo Fallero',NULL,'http://www.valencia.es/ayuntamiento/Infociudad_accesible.nsf/vDocumentosWebListado/78EC309BC73848FAC12572C20023FE07',NULL),(9,'4','Plaza de Toros',NULL,'http://plaza-toros-valencia.blogspot.com/',NULL),(10,'5','Gulliver',NULL,'http://www.valencia.es/ayuntamiento/Infociudad_accesible.nsf/vDocumentosWebListado/FF3D19B069970317C12572C2002430AE?OpenDocument&bdOrigen',NULL),(11,'5','Bioparc',NULL,'https://www.bioparcvalencia.es/',NULL),(12,'6','Nou Borraset',NULL,'http://naoborraset.com/',NULL),(13,'6','Restaurante Ricard Camarena',NULL,'https://www.ricardcamarenarestaurant.com/',NULL),(14,'7','Siete Sushi',NULL,'https://www.sietesushi.com/',NULL),(15,'7','Restaurante Aoyama',NULL,'http://www.restauranteaoyama.es/',NULL),(16,'8','Kebab Bolsería',NULL,'http://kebapbolseria.com/menu-kebap-bolseria.htm',NULL),(17,'8','HardRock Café',NULL,'https://www.hardrockcafe.com/location/valencia/#utm_source=Google&utm_medium=Yext&utm_campaign=Listings',NULL),(18,'9','AlmaLibre VeganFood',NULL,'https://www.almalibreacaibar.es/',NULL),(19,'9','The Burger',NULL,'https://thevurger.com/',NULL),(20,'10','McDonalds',NULL,'https://www.mcdonalds-mcdelivery.es/index.html?utm_source=valencia&utm_medium=mybusiness&utm_campaign=valencia',NULL),(21,'10','Taco Bell',NULL,'https://tacobell.es/',NULL),(22,'11','Senator Parque Central Hotel',NULL,'https://www.senatorparquecentralhotel.com/',NULL),(23,'11','Hotel Melià Valencia',NULL,'https://www.melia.com/es/hoteles/espana/valencia/melia-valencia/index.html',NULL),(24,'12','Sercotel Sorolla Palace',NULL,'https://www.sercotelhoteles.com/hotel-sorolla-palace/ofertas/?gclid=CjwKCAjw1v_0BRAkEiwALFkj5ghZaccZjKDqbZkHj3wWL5vWXuplV4d1v8NzbBh0GGp2sKyt5w00qhoCKP0QAvD_BwE',NULL),(25,'12','NH Valencia Las Artes',NULL,'https://www.nh-hoteles.es/hotel/',NULL),(26,'13','Happy Apartments Valencia',NULL,'http://www.happyapartmentsvalencia.com/',NULL),(27,'13','Apartamentos Centro Valencia',NULL,'https://www.apartamentoscentrovalencia.com/',NULL),(28,'14','Hotel Las Arenas',NULL,'https://www.hotelvalencialasarenas.com/',NULL),(29,'15','Bquarto Alquileres',NULL,'https://www.bquarto.es/valencia/habitaciones-en-alquiler-valencia?mas=3&gclid=CjwKCAjw1v_0BRAkEiwALFkj5tsyob4vN5EU6tTI-JdXHB71zDGZuBz3_e9lgRD14RrKd6bNQ23YYRoCIy4QAvD_BwE',NULL),(30,'16','Valencia Esencial',NULL,'https://freetourvalencia.com/blog/tours/visita-guiada-valencia/',NULL),(31,'16','Tour Albufera',NULL,'https://freetouralbufera.com/',NULL),(32,'17','Misterios y Leyendas',NULL,'https://freetourvalencia.com/blog/tours/visita-guiada-valencia/',NULL),(33,'17','Historia de Valencia',NULL,'https://freetouralbufera.com/',NULL);
/*!40000 ALTER TABLE `actividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallepacks`
--

DROP TABLE IF EXISTS `detallepacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallepacks` (
  `idPack` int(11) NOT NULL,
  `numLinea` int(11) NOT NULL,
  `idActividad` int(11) NOT NULL,
  `precioActividad` decimal(10,2) NOT NULL,
  `numeroPlazas` int(11) NOT NULL,
  `FechaInicio` datetime NOT NULL,
  `fechaFinal` datetime NOT NULL,
  `duracion` time NOT NULL,
  PRIMARY KEY (`idPack`,`numLinea`),
  KEY `fk_packs_Actividades_Actividades1_idx` (`idActividad`),
  KEY `fk_packs_Actividades_packs1_idx` (`idPack`),
  CONSTRAINT `fk_packs_Actividades_Actividades1` FOREIGN KEY (`idActividad`) REFERENCES `actividades` (`idActividad`),
  CONSTRAINT `fk_packs_Actividades_packs1` FOREIGN KEY (`idPack`) REFERENCES `packs` (`idPack`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepacks`
--

LOCK TABLES `detallepacks` WRITE;
/*!40000 ALTER TABLE `detallepacks` DISABLE KEYS */;
INSERT INTO `detallepacks` VALUES (1,1,1,8.50,2,'2020-05-20 10:00:00','2020-05-20 14:00:00','04:00:00'),(1,2,2,10.50,2,'2020-05-20 15:00:00','2020-05-20 17:00:00','02:00:00'),(1,3,25,300.00,2,'2020-05-20 12:00:00','2020-05-25 12:00:00','120:00:00'),(1,4,20,25.00,1,'2020-04-29 12:00:00','2020-04-29 12:30:00','02:00:00'),(1,5,20,25.00,1,'2020-05-29 12:00:00','2020-05-29 12:30:00','02:00:00'),(2,1,25,200.00,2,'2020-05-21 12:00:00','2020-05-24 12:00:00','72:00:00');
/*!40000 ALTER TABLE `detallepacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packs`
--

DROP TABLE IF EXISTS `packs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packs` (
  `idPack` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` text,
  `fechaPack` date NOT NULL,
  `favorito` tinyint(4) NOT NULL,
  PRIMARY KEY (`idPack`),
  KEY `fk_packs_Usuarios_idx` (`idUsuario`),
  CONSTRAINT `fk_packs_Usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packs`
--

LOCK TABLES `packs` WRITE;
/*!40000 ALTER TABLE `packs` DISABLE KEYS */;
INSERT INTO `packs` VALUES (1,1,'PACK DEL AMOR',NULL,'2020-04-22',1),(2,2,'MI PACK VACAS','MIS VACACIONES DE SEMANA SANTA','2020-04-22',0),(3,4,'PACKS',NULL,'2020-04-22',0);
/*!40000 ALTER TABLE `packs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subtipo`
--

DROP TABLE IF EXISTS `subtipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subtipo` (
  `codigoSubtipo` varchar(4) NOT NULL,
  `codigoTipo` varchar(4) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `logoSubTipo` blob,
  PRIMARY KEY (`codigoSubtipo`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `fk_subtipo_tipo1_idx` (`codigoTipo`),
  CONSTRAINT `fk_subtipo_tipo1` FOREIGN KEY (`codigoTipo`) REFERENCES `tipo` (`codigoTipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtipo`
--

LOCK TABLES `subtipo` WRITE;
/*!40000 ALTER TABLE `subtipo` DISABLE KEYS */;
INSERT INTO `subtipo` VALUES ('1','1','Religioso',NULL),('10','2','Económico',NULL),('11','3','Barato',NULL),('12','3','Familiar',NULL),('13','3','Apartamento',NULL),('14','3','Lujo',NULL),('15','3','Habitación Particular',NULL),('16','4','FreeTour',NULL),('17','4','Tour de Pago',NULL),('2','1','Ciencia',NULL),('3','1','Aire Libre',NULL),('4','1','Folcrórico',NULL),('5','1','Infantil',NULL),('6','2','Valenciano',NULL),('7','2','Asiático',NULL),('8','2','Comida Rápida',NULL),('9','2','Vegano',NULL);
/*!40000 ALTER TABLE `subtipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo` (
  `codigoTipo` varchar(4) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`codigoTipo`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` VALUES ('3','Alojamiento'),('1','Localizaciones'),('2','Restauración'),('4','Tours');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` int(13) NOT NULL,
  `rol` enum('cliente','administrador') NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  `tarjetaCredito` int(11) DEFAULT NULL,
  `gustos` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `correo_electronico_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Manuel','Serrano Ruiz','ejemplo1@gmail.com',961511020,'cliente','jajajaja1',4485718,'correr'),(2,'Pepe','Serrano Ruiz','ejemplo2@gmail.com',961511520,'cliente','jajajaja2',5485718,'Nadar'),(3,'Luis','Ferrer Vázquez','ejemplo3@gmail.com',65125489,'cliente','jajajaja3',52146985,'correr'),(4,'Alberto','Ruiperez Ruiz','ejemplo4@gmail.com',96547821,'cliente','jajajaja4',4444555,'Mear'),(5,'Pendejo','Juarez','ejemplo5@gmail.com',65873321,'administrador','jajajaja6',5647821,'Mear'),(6,'Francisco Javier','Gonzalez Valera','javigon93@gmail.com',679033026,'administrador','Gonzalez_Landete',5647821,'Mear'),(7,'Nerea','Landete Bernat','nerealandetebernat@gmail.com',619743419,'cliente','Bubu',5647821,'Pipi');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-13 14:55:27
