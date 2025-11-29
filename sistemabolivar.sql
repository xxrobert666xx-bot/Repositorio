-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-07-2025 a las 02:34:22
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemabolivar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE `articulo` (
  `idarticulo` int(11) NOT NULL,
  `idcategoria` int(11) NOT NULL,
  `codigo` varchar(50) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `stock` int(11) DEFAULT NULL,
  `precio_venta` decimal(11,2) NOT NULL DEFAULT 0.00,
  `descripcion` varchar(256) DEFAULT NULL,
  `imagen` varchar(50) DEFAULT NULL,
  `condicion` tinyint(4) DEFAULT 1,
  `precio_compra` decimal(11,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`idarticulo`, `idcategoria`, `codigo`, `nombre`, `stock`, `precio_venta`, `descripcion`, `imagen`, `condicion`, `precio_compra`) VALUES
(1, 1, 'CPU-INT-001', 'Procesador Intel Core i9-14900K', 219, 2800.00, '24 núcleos (8 P-cores + 16 E-cores), hasta 6.0 GHz.', '', 1, 0.00),
(2, 1, 'CPU-AMD-001', 'Procesador AMD Ryzen 9 7950X', 0, 2650.00, '16 núcleos y 32 hilos, hasta 5.7 GHz, socket AM5.', '', 1, 0.00),
(3, 2, 'MB-ASU-001', 'Placa Madre ASUS ROG STRIX Z790-E GAMING WIFI II', 107, 2400.00, 'Soporte para DDR5, PCIe 5.0, Wi-Fi 6E, para Intel Gen 12/13/14.', '', 1, 0.00),
(4, 2, 'MB-GIG-001', 'Placa Madre Gigabyte B650 AORUS ELITE AX', 65, 950.00, 'Soporte para DDR5, PCIe 5.0, Wi-Fi 6E, para AMD Ryzen 7000.', '', 1, 0.00),
(5, 3, 'RAM-COR-001', 'Memoria RAM Corsair Vengeance DDR5 32GB (2x16GB) 6000MHz', 120, 550.00, 'Kit de dos módulos, CL36, optimizado para Intel XMP 3.0.', '', 1, 0.00),
(6, 3, 'RAM-KIN-001', 'Memoria RAM Kingston FURY Beast DDR4 16GB (2x8GB) 3200MHz', 0, 280.00, 'Kit de dos módulos, ideal para gaming y productividad.', '', 1, 0.00),
(7, 4, 'GPU-NVI-001', 'Tarjeta Gráfica NVIDIA GeForce RTX 4070 Ti SUPER 16GB', 0, 4500.00, '16GB GDDR6X, arquitectura Ada Lovelace, DLSS 3.', '', 1, 0.00),
(8, 4, 'GPU-AMD-001', 'Tarjeta Gráfica AMD Radeon RX 7800 XT 16GB', 0, 2900.00, '16GB GDDR6, arquitectura RDNA 3, ideal para 1440p.', '', 1, 0.00),
(9, 5, 'SSD-SAM-001', 'SSD Samsung 990 PRO 2TB NVMe M.2', 0, 980.00, 'Unidad de estado sólido con velocidades de lectura de hasta 7450 MB/s.', '', 1, 0.00),
(10, 5, 'SSD-CRU-001', 'SSD Crucial MX500 1TB SATA III', 0, 420.00, 'Unidad de estado sólido de 2.5 pulgadas, ideal para actualizar PCs y laptops.', '', 1, 0.00),
(11, 6, 'HDD-SEA-001', 'Disco Duro Seagate Barracuda 4TB 3.5\"', 1, 400.00, '7200 RPM, SATA 6Gb/s, para almacenamiento masivo en PC.', '', 1, 0.00),
(12, 7, 'PSU-COR-001', 'Fuente de Poder Corsair RM850x 850W 80+ Gold Modular', 39, 750.00, 'Fuente de alimentación completamente modular y silenciosa.', '', 1, 0.00),
(13, 8, 'CASE-NXZ-001', 'Gabinete NZXT H5 Flow', 20, 450.00, 'Gabinete Mid-Tower con panel frontal perforado para alto flujo de aire.', '', 1, 0.00),
(14, 9, 'COOL-NOC-001', 'Refrigeración por Aire Noctua NH-D15', 9, 520.00, 'Disipador de doble torre de alto rendimiento y bajo ruido.', '', 1, 0.00),
(15, 10, 'NET-TP-001', 'Tarjeta de Red TP-Link Archer TX3000E Wi-Fi 6 Bluetooth 5.0', 0, 250.00, 'Tarjeta de expansión PCIe para conectividad inalámbrica de alta velocidad.', '', 1, 0.00),
(16, 11, 'LAP-LEN-001', 'Laptop Lenovo Legion 5 Pro (Ryzen 7, RTX 4060)', 0, 6500.00, '16\" QHD+ 165Hz, 16GB DDR5, 1TB SSD, ideal para gaming.', '', 1, 0.00),
(17, 11, 'LAP-DEL-001', 'Laptop Dell XPS 15 (Core i7, 16GB RAM)', 0, 7200.00, 'Pantalla 15.6\" FHD+, 16GB DDR5, 512GB SSD, para profesionales.', '', 1, 0.00),
(18, 12, 'PC-HP-001', 'Computadora de Escritorio HP Pavilion Gaming TG01', 0, 3800.00, 'Core i5, 16GB RAM, 512GB SSD, RTX 3050.', '', 1, 0.00),
(19, 13, 'AIO-APP-001', 'Apple iMac 24\" (Chip M3)', 0, 7500.00, 'Chip M3, 8GB RAM, 256GB SSD, pantalla Retina 4.5K.', '', 1, 0.00),
(20, 14, 'SRV-DEL-001', 'Servidor Dell PowerEdge T150 Torre', 0, 4800.00, 'Intel Xeon E-2314, 16GB ECC RAM, 1TB HDD, para pequeñas empresas.', '', 1, 0.00),
(21, 15, 'WKS-HP-001', 'Estación de Trabajo HP Z2 G9', 0, 8500.00, 'Intel Core i7, 32GB RAM, 1TB NVMe SSD, NVIDIA Quadro T1000.', '', 1, 0.00),
(22, 16, 'NAS-SYN-001', 'NAS Synology DiskStation DS224+', 9, 1800.00, 'Servidor de almacenamiento en red de 2 bahías (sin discos).', '', 1, 0.00),
(23, 17, 'TAB-SAM-001', 'Tablet Samsung Galaxy Tab S9 FE', 98, 2100.00, 'Pantalla 10.9\", 6GB RAM, 128GB, S Pen incluido.', '', 1, 0.00),
(24, 18, 'CEL-SAM-001', 'Smartphone Samsung Galaxy S24 Ultra 256GB', 112, 5200.00, 'Galaxy AI, S Pen integrado, cámara de 200MP.', '', 1, 0.00),
(25, 19, 'DRO-DJI-001', 'Drone DJI Mini 4 Pro', 28, 4500.00, 'Video 4K/60fps HDR, detección de obstáculos omnidireccional.', '', 1, 0.00),
(26, 20, 'SBC-RAS-001', 'Raspberry Pi 5 8GB', 0, 480.00, 'Computadora de placa única para desarrolladores y entusiastas.', '', 1, 0.00),
(27, 21, 'MON-DEL-001', 'Monitor Dell UltraSharp U2723QE 27\" 4K', 0, 2800.00, 'Panel IPS Black, 4K UHD, 98% DCI-P3, Hub USB-C.', '', 1, 0.00),
(28, 21, 'MON-LG-001', 'Monitor LG UltraGear 27GP850-B 27\" QHD', 0, 1900.00, 'Panel Nano IPS, 165Hz, 1ms, G-Sync compatible.', '', 1, 0.00),
(29, 22, 'KEY-LOG-001', 'Teclado Mecánico Logitech G PRO X TKL', 0, 750.00, 'Inalámbrico LIGHTSPEED, interruptores intercambiables.', '', 1, 0.00),
(30, 22, 'KEY-MIC-001', 'Teclado Ergonómico Microsoft Sculpt', 0, 450.00, 'Diseño dividido para una postura natural de las muñecas.', '', 1, 0.00),
(31, 23, 'MOU-LOG-001', 'Mouse Inalámbrico Logitech MX Master 3S', 0, 480.00, 'Sensor de 8K DPI, clics silenciosos, scroll electromagnético.', '', 1, 0.00),
(32, 23, 'MOU-RAZ-001', 'Mouse Gamer Razer DeathAdder V3 Pro', 0, 650.00, 'Ultraligero (63g), sensor óptico Focus Pro 30K, inalámbrico.', '', 1, 0.00),
(33, 24, 'HEAD-SON-001', 'Auriculares Sony WH-1000XM5 con Cancelación de Ruido', 0, 1600.00, 'Líder en cancelación de ruido, audio de alta resolución.', '', 1, 0.00),
(34, 25, 'WEB-LOG-001', 'Webcam Logitech C920s Pro HD', 0, 320.00, 'Video Full HD 1080p, con tapa de privacidad.', '', 1, 0.00),
(35, 26, 'IMP-EPS-001', 'Impresora Epson EcoTank L3250 Multifuncional', 0, 750.00, 'Sistema de tanque de tinta, impresión económica, Wi-Fi.', '', 1, 0.00),
(36, 26, 'IMP-BRO-001', 'Impresora Láser Brother HL-L2350DW Monocromática', 0, 680.00, 'Impresión dúplex automática, Wi-Fi, compacta.', '', 1, 0.00),
(37, 27, 'SCAN-EPS-001', 'Escáner Epson WorkForce ES-50 Portátil', 0, 550.00, 'Escáner de documentos a color, alimentado por USB.', '', 1, 0.00),
(38, 28, 'PROY-EPS-001', 'Proyector Epson Home Cinema 1080', 0, 3200.00, '3400 lúmenes, Full HD, ideal para presentaciones.', '', 1, 0.00),
(39, 29, 'SPK-LOG-001', 'Altavoces Logitech Z407 con Subwoofer Bluetooth', 0, 520.00, '80W de potencia máxima, control de dial inalámbrico.', '', 1, 0.00),
(40, 30, 'TAB-WAC-001', 'Tableta Digitalizadora Wacom Intuos Pro M', 0, 1600.00, 'Sensibilidad a la presión de 8192 niveles, Bluetooth.', '', 1, 0.00),
(41, 31, 'ROU-TP-001', 'Router TP-Link Archer AX73 Wi-Fi 6', 0, 780.00, 'Doble banda AX5400, 6 antenas, HomeShield Security.', '', 1, 0.00),
(42, 32, 'SWI-UBI-001', 'Switch Ubiquiti UniFi Switch Lite 8 PoE', 0, 650.00, 'Switch gestionable de 8 puertos Gigabit, 4 con PoE+.', '', 1, 0.00),
(43, 33, 'AP-UBI-001', 'Punto de Acceso Ubiquiti UniFi 6 Lite (U6-Lite)', 0, 580.00, 'Punto de acceso Wi-Fi 6 de doble banda.', '', 1, 0.00),
(44, 34, 'NET-INT-001', 'Adaptador de Red Intel Gigabit CT', 0, 150.00, 'Tarjeta de red PCIe x1, 10/100/1000 Mbps.', '', 1, 0.00),
(45, 35, 'CBL-ETH-001', 'Cable de Red Cat 6 UTP 20 metros', 0, 60.00, 'Cable de par trenzado no apantallado para redes Gigabit.', '', 1, 0.00),
(46, 36, 'FWL-FS-001', 'Firewall Fortinet FortiGate 40F', 0, 2500.00, 'Firewall de hardware UTM para seguridad perimetral.', '', 1, 0.00),
(47, 38, 'REP-TP-001', 'Repetidor de Señal TP-Link RE550 AC1900', 0, 280.00, 'Extensor de rango Wi-Fi de doble banda.', '', 1, 0.00),
(48, 40, 'SFP-UBI-001', 'Módulo Transceptor Ubiquiti SFP+ 10G', 0, 220.00, 'Módulo Multi-Modo para conexiones de fibra óptica de corto alcance.', '', 1, 0.00),
(49, 41, 'OS-WIN-001', 'Licencia Windows 11 Pro 64-bit', 188, 650.00, 'Licencia OEM para sistema operativo profesional.', '', 1, 0.00),
(50, 42, 'SOFT-MS-001', 'Suscripción Microsoft 365 Personal (1 Año)', 0, 220.00, 'Incluye Word, Excel, PowerPoint, Outlook y 1TB en OneDrive.', '', 1, 0.00),
(51, 43, 'SEC-KASP-001', 'Antivirus Kaspersky Standard (1 Dispositivo, 1 Año)', 0, 90.00, 'Protección esencial contra virus y malware.', '', 1, 0.00),
(52, 44, 'UPS-APC-001', 'UPS APC Back-UPS 650VA', 0, 380.00, 'Sistema de alimentación ininterrumpida con 4 tomas de respaldo.', '', 1, 0.00),
(53, 44, 'UPS-FOR-001', 'UPS Forza NT-1011 1000VA', 0, 450.00, '1000VA/500W, 6 tomas, ideal para PCs y estaciones de trabajo.', '', 1, 0.00),
(54, 45, 'CBL-HDMI-001', 'Cable HDMI 2.1 Ultra High Speed 3 metros', 0, 80.00, 'Soporta 8K@60Hz y 4K@120Hz.', '', 1, 0.00),
(55, 45, 'ADP-USBC-001', 'Adaptador Hub USB-C 7 en 1', 0, 180.00, 'Incluye HDMI, 3xUSB 3.0, lector SD/MicroSD y Power Delivery.', '', 1, 0.00),
(56, 46, 'CLEAN-KIT-001', 'Kit de Limpieza para Electrónicos', 0, 50.00, 'Incluye soplador de aire, cepillos y paños de microfibra.', '', 1, 0.00),
(57, 47, 'CASE-THU-001', 'Mochila para Laptop Thule Subterra 15\"', 0, 550.00, 'Mochila resistente al agua con compartimento para laptop de 15.6\".', '', 1, 0.00),
(58, 48, 'ARM-ERG-001', 'Brazo Ergonómico para Monitor (17-32 pulgadas)', 0, 250.00, 'Soporte de escritorio con resorte de gas para ajuste de altura.', '', 1, 0.00),
(59, 49, 'POS-SCAN-001', 'Lector de Código de Barras 1D/2D USB', 0, 280.00, 'Escáner de mano con cable para códigos de barras y QR.', '', 1, 0.00),
(60, 49, 'POS-PRINT-001', 'Impresora de Recibos Térmica 80mm', 0, 420.00, 'Impresora térmica con corte automático, conexión USB/Ethernet.', '', 1, 0.00),
(61, 50, 'VID-HIK-001', 'Kit de 4 Cámaras de Seguridad Hikvision 1080p con DVR', 0, 850.00, 'Kit completo de videovigilancia con 4 cámaras tipo bala y DVR.', '', 1, 0.00),
(62, 50, 'VID-TP-001', 'Cámara de Seguridad TP-Link Tapo C200 Wi-Fi', 0, 150.00, 'Cámara IP 1080p con movimiento panorámico/inclinación.', '', 1, 0.00),
(63, 1, 'CPU-INT-002', 'Procesador Intel Core i5-14600K', 0, 1450.00, '14 núcleos (6 P-cores + 8 E-cores), hasta 5.3 GHz.', '', 1, 0.00),
(64, 5, 'SSD-KIN-001', 'SSD Kingston NV2 1TB NVMe M.2', 0, 390.00, 'Unidad de estado sólido PCIe 4.0 con buena relación rendimiento/precio.', '', 1, 0.00),
(65, 11, 'LAP-HP-001', 'Laptop HP Envy x360 15\"', 0, 4800.00, 'Convertible 2-en-1, Ryzen 5, 8GB RAM, 512GB SSD, pantalla táctil.', '', 1, 0.00),
(66, 17, 'TAB-APP-001', 'Apple iPad 10.9\" (10a Generación) 64GB', 0, 1950.00, 'Chip A14 Bionic, pantalla Liquid Retina, Wi-Fi.', '', 1, 0.00),
(67, 21, 'MON-SAMS-001', 'Monitor Samsung Odyssey G5 32\" Curvo QHD', 0, 1700.00, 'Curvatura 1000R, 144Hz, 1ms, para una experiencia inmersiva.', '', 1, 0.00),
(68, 22, 'KEY-RAZ-001', 'Teclado Mecánico Razer Huntsman Mini', 0, 580.00, 'Formato 60% con interruptores ópticos Razer.', '', 1, 0.00),
(69, 31, 'ROU-ASU-001', 'Router ASUS RT-AX86U Pro Wi-Fi 6', 0, 1100.00, 'Doble banda AX5700, modo gaming móvil, puerto 2.5G.', '', 1, 0.00),
(70, 44, 'UPS-CDP-001', 'UPS CDP 1000VA Interactivo', 0, 480.00, '1000VA/600W, con regulación de voltaje (AVR).', '', 1, 0.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE `auditoria` (
  `idauditoria` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `tabla_afectada` varchar(50) NOT NULL,
  `accion` varchar(50) NOT NULL,
  `datos_anteriores` text DEFAULT NULL,
  `datos_nuevos` text DEFAULT NULL,
  `fecha_hora` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `auditoria`
--

INSERT INTO `auditoria` (`idauditoria`, `idusuario`, `tabla_afectada`, `accion`, `datos_anteriores`, `datos_nuevos`, `fecha_hora`) VALUES
(1, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 36, \"idventa\": 28, \"idarticulo\": 2, \"cantidad\": 21, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-23 19:58:37'),
(2, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 37, \"idventa\": 29, \"idarticulo\": 1, \"cantidad\": 10, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-25 17:31:51'),
(3, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 38, \"idventa\": 30, \"idarticulo\": 5, \"cantidad\": 50, \"precio_venta\": 0.00, \"descuento\": 0.00}', '2025-06-25 19:19:20'),
(4, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 39, \"idventa\": 31, \"idarticulo\": 5, \"cantidad\": 50, \"precio_venta\": 0.00, \"descuento\": 0.00}', '2025-06-25 19:29:01'),
(5, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 40, \"idventa\": 32, \"idarticulo\": 1, \"cantidad\": 10, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-25 19:36:49'),
(6, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 41, \"idventa\": 33, \"idarticulo\": 1, \"cantidad\": 3, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-25 19:50:10'),
(7, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 42, \"idventa\": 34, \"idarticulo\": 1, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-25 19:56:12'),
(8, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 43, \"idventa\": 35, \"idarticulo\": 1, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-25 19:58:39'),
(9, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 44, \"idventa\": 36, \"idarticulo\": 1, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-25 20:02:39'),
(10, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 45, \"idventa\": 37, \"idarticulo\": 1, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-25 20:11:58'),
(11, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 46, \"idventa\": 38, \"idarticulo\": 1, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-25 20:26:06'),
(12, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 47, \"idventa\": 39, \"idarticulo\": 6, \"cantidad\": 1, \"precio_venta\": 0.00, \"descuento\": 0.00}', '2025-06-25 20:31:52'),
(13, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 48, \"idventa\": 40, \"idarticulo\": 6, \"cantidad\": 1, \"precio_venta\": 0.00, \"descuento\": 0.00}', '2025-06-25 20:32:19'),
(14, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 49, \"idventa\": 41, \"idarticulo\": 6, \"cantidad\": 1, \"precio_venta\": 0.00, \"descuento\": 0.00}', '2025-06-25 20:36:26'),
(15, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 50, \"idventa\": 42, \"idarticulo\": 6, \"cantidad\": 1, \"precio_venta\": 0.00, \"descuento\": 0.00}', '2025-06-25 21:15:01'),
(16, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 51, \"idventa\": 43, \"idarticulo\": 3, \"cantidad\": 1, \"precio_venta\": 800.00, \"descuento\": 0.00}', '2025-06-25 23:50:01'),
(17, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 52, \"idventa\": 43, \"idarticulo\": 17, \"cantidad\": 1, \"precio_venta\": 32.00, \"descuento\": 0.00}', '2025-06-25 23:50:01'),
(18, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 53, \"idventa\": 44, \"idarticulo\": 4, \"cantidad\": 1, \"precio_venta\": 1500.00, \"descuento\": 0.00}', '2025-06-25 23:51:00'),
(19, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 54, \"idventa\": 45, \"idarticulo\": 2, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.15}', '2025-06-25 23:57:40'),
(20, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 55, \"idventa\": 46, \"idarticulo\": 3, \"cantidad\": 1, \"precio_venta\": 800.00, \"descuento\": 0.00}', '2025-06-26 00:09:53'),
(21, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 56, \"idventa\": 47, \"idarticulo\": 2, \"cantidad\": 10, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-26 14:59:30'),
(22, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 57, \"idventa\": 48, \"idarticulo\": 2, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-26 15:00:22'),
(23, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 58, \"idventa\": 49, \"idarticulo\": 2, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-06-26 15:02:53'),
(24, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 59, \"idventa\": 50, \"idarticulo\": 2, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-07-03 15:39:23'),
(25, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 60, \"idventa\": 51, \"idarticulo\": 2, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-07-03 15:42:21'),
(26, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 61, \"idventa\": 51, \"idarticulo\": 3, \"cantidad\": 1, \"precio_venta\": 800.00, \"descuento\": 0.00}', '2025-07-03 15:42:21'),
(27, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 62, \"idventa\": 51, \"idarticulo\": 4, \"cantidad\": 1, \"precio_venta\": 1500.00, \"descuento\": 0.00}', '2025-07-03 15:42:21'),
(28, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 63, \"idventa\": 52, \"idarticulo\": 2, \"cantidad\": 1, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-07-03 15:43:27'),
(29, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 64, \"idventa\": 53, \"idarticulo\": 3, \"cantidad\": 2, \"precio_venta\": 800.00, \"descuento\": 0.00}', '2025-07-03 16:56:48'),
(30, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 65, \"idventa\": 54, \"idarticulo\": 3, \"cantidad\": 2, \"precio_venta\": 800.00, \"descuento\": 0.00}', '2025-07-03 17:01:16'),
(31, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 66, \"idventa\": 55, \"idarticulo\": 2, \"cantidad\": 5, \"precio_venta\": 1.00, \"descuento\": 0.00}', '2025-07-03 17:02:42'),
(32, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 67, \"idventa\": 56, \"idarticulo\": 4, \"cantidad\": 2, \"precio_venta\": 1500.00, \"descuento\": 0.00}', '2025-07-03 17:45:18'),
(33, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 68, \"idventa\": 57, \"idarticulo\": 3, \"cantidad\": 2, \"precio_venta\": 2400.00, \"descuento\": 0.00}', '2025-07-03 20:23:28'),
(34, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 69, \"idventa\": 57, \"idarticulo\": 11, \"cantidad\": 4, \"precio_venta\": 400.00, \"descuento\": 0.00}', '2025-07-03 20:23:28'),
(35, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 70, \"idventa\": 58, \"idarticulo\": 5, \"cantidad\": 10, \"precio_venta\": 550.00, \"descuento\": 0.00}', '2025-07-03 20:31:04'),
(36, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 71, \"idventa\": 58, \"idarticulo\": 23, \"cantidad\": 1, \"precio_venta\": 2100.00, \"descuento\": 0.00}', '2025-07-03 20:31:04'),
(37, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 72, \"idventa\": 59, \"idarticulo\": 1, \"cantidad\": 2, \"precio_venta\": 2800.00, \"descuento\": 0.00}', '2025-07-03 20:32:12'),
(38, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 73, \"idventa\": 60, \"idarticulo\": 13, \"cantidad\": 1, \"precio_venta\": 450.00, \"descuento\": 0.00}', '2025-07-03 20:34:24'),
(39, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 74, \"idventa\": 61, \"idarticulo\": 25, \"cantidad\": 1, \"precio_venta\": 4500.00, \"descuento\": 0.00}', '2025-07-03 20:39:52'),
(40, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 75, \"idventa\": 62, \"idarticulo\": 1, \"cantidad\": 10, \"precio_venta\": 2800.00, \"descuento\": 0.00}', '2025-07-03 20:48:51'),
(41, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 76, \"idventa\": 63, \"idarticulo\": 24, \"cantidad\": 8, \"precio_venta\": 5200.00, \"descuento\": 0.00}', '2025-07-03 20:49:54'),
(42, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 77, \"idventa\": 64, \"idarticulo\": 3, \"cantidad\": 1, \"precio_venta\": 2400.00, \"descuento\": 0.00}', '2025-07-03 20:59:57'),
(43, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 78, \"idventa\": 65, \"idarticulo\": 1, \"cantidad\": 1, \"precio_venta\": 2800.00, \"descuento\": 0.00}', '2025-07-03 21:01:04'),
(44, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 79, \"idventa\": 66, \"idarticulo\": 1, \"cantidad\": 1, \"precio_venta\": 2800.00, \"descuento\": 0.00}', '2025-07-03 21:01:30'),
(45, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 80, \"idventa\": 67, \"idarticulo\": 12, \"cantidad\": 1, \"precio_venta\": 750.00, \"descuento\": 0.00}', '2025-07-03 21:04:04'),
(46, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 81, \"idventa\": 68, \"idarticulo\": 12, \"cantidad\": 1, \"precio_venta\": 750.00, \"descuento\": 0.00}', '2025-07-03 21:05:09'),
(47, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 82, \"idventa\": 69, \"idarticulo\": 1, \"cantidad\": 1, \"precio_venta\": 2800.00, \"descuento\": 0.00}', '2025-07-03 21:05:45'),
(48, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 83, \"idventa\": 69, \"idarticulo\": 25, \"cantidad\": 1, \"precio_venta\": 4500.00, \"descuento\": 0.00}', '2025-07-03 21:05:45'),
(49, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 84, \"idventa\": 69, \"idarticulo\": 14, \"cantidad\": 1, \"precio_venta\": 520.00, \"descuento\": 0.00}', '2025-07-03 21:05:45'),
(50, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 85, \"idventa\": 70, \"idarticulo\": 23, \"cantidad\": 1, \"precio_venta\": 2100.00, \"descuento\": 336.00}', '2025-07-03 23:18:32'),
(51, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 86, \"idventa\": 70, \"idarticulo\": 22, \"cantidad\": 1, \"precio_venta\": 1800.00, \"descuento\": 0.00}', '2025-07-03 23:18:32'),
(52, 1, 'detalle_venta', 'INSERT', NULL, '{\"iddetalle_venta\": 87, \"idventa\": 71, \"idarticulo\": 49, \"cantidad\": 1, \"precio_venta\": 650.00, \"descuento\": 162.50}', '2025-07-04 10:24:13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `idcategoria` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(256) DEFAULT NULL,
  `condicion` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idcategoria`, `nombre`, `descripcion`, `condicion`) VALUES
(1, 'PROCESADORES (CPU)', 'Unidades Centrales de Procesamiento de Intel y AMD para servidores y estaciones de trabajo.', 1),
(2, 'PLACAS MADRE', 'Motherboards para diversas plataformas y factores de forma, incluyendo ATX, Micro-ATX y Mini-ITX.', 1),
(3, 'MEMORIAS RAM', 'Módulos de memoria RAM DDR4 y DDR5 para laptops, PCs de escritorio y servidores (ECC y non-ECC).', 1),
(4, 'TARJETAS GRÁFICAS (GPU)', 'Tarjetas de video NVIDIA GeForce/Quadro y AMD Radeon/Radeon Pro para gaming y computación profesional.', 1),
(5, 'ALMACENAMIENTO SSD', 'Unidades de Estado Sólido (SSD) internas y externas en formatos SATA, M.2 NVMe y U.2.', 1),
(6, 'ALMACENAMIENTO HDD', 'Discos Duros (HDD) de alta capacidad para almacenamiento masivo y sistemas NAS/servidores.', 1),
(7, 'FUENTES DE PODER (PSU)', 'Fuentes de alimentación con certificaciones 80 Plus (Bronze, Gold, Platinum) para eficiencia energética.', 1),
(8, 'GABINETES Y CHASIS', 'Gabinetes (cases) para PC de tipo torre, SFF (Small Form Factor) y para montaje en rack de servidores.', 1),
(9, 'REFRIGERACIÓN Y VENTILACIÓN', 'Sistemas de refrigeración por aire, líquida (AIO) y ventiladores para mantener temperaturas óptimas.', 1),
(10, 'TARJETAS DE EXPANSIÓN', 'Tarjetas de sonido, red, controladoras RAID y otros componentes para expandir funcionalidades.', 1),
(11, 'LAPTOPS Y NOTEBOOKS', 'Portátiles de uso general, ultrabooks, estaciones de trabajo móviles y laptops para gaming.', 1),
(12, 'COMPUTADORAS DE ESCRITORIO', 'PCs de escritorio pre-ensambladas y configuraciones personalizadas para oficina y uso profesional.', 1),
(13, 'ALL-IN-ONE (AIO)', 'Equipos todo en uno que integran el monitor y la CPU, ideales para espacios de trabajo limpios.', 1),
(14, 'SERVIDORES', 'Servidores de torre, para rack (rackmount) y blade para centros de datos e infraestructura empresarial.', 1),
(15, 'ESTACIONES DE TRABAJO', 'Equipos de alto rendimiento optimizados para diseño, CAD, edición de video y cálculo científico.', 1),
(16, 'ALMACENAMIENTO EN RED (NAS/SAN)', 'Sistemas Network Attached Storage (NAS) y Storage Area Network (SAN) para almacenamiento centralizado.', 1),
(17, 'TABLETS Y DISPOSITIVOS 2-EN-1', 'Tablets con Android/iOS y dispositivos convertibles con Windows para máxima portabilidad.', 1),
(18, 'SMARTPHONES Y MÓVILES', 'Teléfonos móviles inteligentes de gama empresarial y de consumo general.', 1),
(19, 'DRONES Y ACCESORIOS', 'Vehículos aéreos no tripulados para fotografía, videografía, inspección industrial y agricultura.', 1),
(20, 'SISTEMAS EMBEBIDOS Y SBC', 'Computadoras de placa única (SBC) como Raspberry Pi y Arduino para proyectos de IoT y prototipado.', 1),
(21, 'MONITORES', 'Monitores para oficina, diseño gráfico (alta fidelidad de color) y gaming (alta tasa de refresco).', 1),
(22, 'TECLADOS', 'Teclados mecánicos, de membrana y ergonómicos, con o sin cable, para todo tipo de usuario.', 1),
(23, 'MOUSES Y RATONES', 'Mouses ópticos, láser, ergonómicos y para gaming, con diferentes niveles de DPI y conectividad.', 1),
(24, 'AURICULARES Y HEADSETS', 'Auriculares con y sin micrófono, para oficina, call centers, gaming y producción de audio.', 1),
(25, 'CÁMARAS WEB Y VIDEOCONFERENCIA', 'Webcams de alta definición y sistemas completos de videoconferencia para salas de reuniones.', 1),
(26, 'IMPRESORAS Y MULTIFUNCIONALES', 'Impresoras de inyección de tinta, láser, matriciales y equipos multifunción (impresión, escaneo, copia).', 1),
(27, 'ESCÁNERES', 'Escáneres de cama plana, de documentos (con alimentador automático) y portátiles.', 1),
(28, 'PROYECTORES MULTIMEDIA', 'Proyectores para presentaciones de negocios, educación y cine en casa con tecnología LCD, DLP y LED.', 1),
(29, 'ALTAVOCES Y SISTEMAS DE SONIDO', 'Sistemas de altavoces estéreo, 2.1, 5.1 y barras de sonido para PCs y salas de conferencias.', 1),
(30, 'TABLETAS DIGITALIZADORAS', 'Tabletas gráficas y lápices ópticos para diseño, ilustración y firma digital.', 1),
(31, 'ROUTERS Y ENRUTADORES', 'Routers inalámbricos (Wi-Fi 5, 6, 6E), balanceadores de carga y routers VPN para redes robustas.', 1),
(32, 'SWITCHES DE RED', 'Switches gestionables y no gestionables, con y sin PoE (Power over Ethernet), para redes locales.', 1),
(33, 'PUNTOS DE ACCESO (AP)', 'Puntos de acceso Wi-Fi para extender la cobertura de red en oficinas, almacenes y espacios públicos.', 1),
(34, 'ADAPTADORES DE RED', 'Tarjetas de red internas (PCIe) y externas (USB) para conectividad Ethernet y Wi-Fi.', 1),
(35, 'CABLES DE RED Y CONECTORES', 'Cables Ethernet (Cat 5e, 6, 6a), fibra óptica y conectores RJ45, paneles de parcheo.', 1),
(36, 'SEGURIDAD DE RED (FIREWALLS)', 'Dispositivos de firewall de hardware (UTM) para proteger la red empresarial de amenazas externas.', 1),
(37, 'MODEMS', 'Modems ADSL, VDSL y de cable para conexión a proveedores de servicios de internet (ISP).', 1),
(38, 'REPETIDORES Y EXTENSORES DE SEÑAL', 'Dispositivos para ampliar el alcance de la señal Wi-Fi y eliminar zonas muertas.', 1),
(39, 'ANTENAS', 'Antenas de alta ganancia para mejorar la recepción y transmisión de señales inalámbricas.', 1),
(40, 'MÓDULOS TRANSCEPTORES (SFP)', 'Módulos SFP, SFP+ y QSFP para conexiones de fibra óptica en switches y servidores.', 1),
(41, 'SOFTWARE DE SISTEMA OPERATIVO', 'Licencias de Windows (Home, Pro, Server), y distribuciones de Linux para empresas.', 1),
(42, 'SOFTWARE DE OFIMÁTICA', 'Suites de oficina como Microsoft 365 y software de colaboración.', 1),
(43, 'SOFTWARE DE SEGURIDAD', 'Licencias de antivirus, anti-malware y soluciones de seguridad de endpoint para empresas.', 1),
(44, 'SISTEMAS SAI/UPS', 'Sistemas de Alimentación Ininterrumpida para proteger equipos contra cortes de energía.', 1),
(45, 'CABLES Y ADAPTADORES', 'Cables de video (HDMI, DisplayPort, VGA), USB (Tipo A, C), de alimentación y adaptadores varios.', 1),
(46, 'ACCESORIOS DE LIMPIEZA', 'Kits de limpieza, aire comprimido y paños de microfibra para mantenimiento de equipos electrónicos.', 1),
(47, 'MALETINES Y MOCHILAS', 'Fundas, maletines y mochilas diseñadas para transportar y proteger equipos portátiles.', 1),
(48, 'SOPORTES Y BRAZOS ERGONÓMICOS', 'Soportes para monitores, laptops y tablets para mejorar la ergonomía del puesto de trabajo.', 1),
(49, 'PUNTO DE VENTA (POS)', 'Terminales de punto de venta, lectores de códigos de barras, impresoras de recibos y cajones de dinero.', 1),
(50, 'SISTEMAS DE VIDEOVIGILANCIA', 'Cámaras de seguridad IP, grabadores (NVR/DVR) y kits completos de CCTV.', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ingreso`
--

CREATE TABLE `detalle_ingreso` (
  `iddetalle_ingreso` int(11) NOT NULL,
  `idingreso` int(11) NOT NULL,
  `idarticulo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_compra` decimal(11,2) NOT NULL,
  `precio_venta` decimal(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `detalle_ingreso`
--

INSERT INTO `detalle_ingreso` (`iddetalle_ingreso`, `idingreso`, `idarticulo`, `cantidad`, `precio_compra`, `precio_venta`) VALUES
(39, 18, 1, 234, 1500.00, 2800.00),
(40, 19, 3, 10, 1100.00, 1500.00),
(41, 20, 5, 120, 80.00, 136.00),
(42, 21, 5, 10, 80.00, 136.00),
(43, 21, 4, 60, 810.00, 1300.00),
(44, 22, 3, 100, 1500.00, 1890.00),
(45, 22, 4, 5, 800.00, 1200.00),
(46, 23, 14, 10, 200.00, 325.00),
(47, 23, 11, 5, 40.00, 55.00),
(48, 23, 13, 20, 60.00, 80.00),
(49, 23, 12, 40, 140.00, 175.00),
(50, 24, 23, 100, 600.00, 1000.00),
(51, 24, 24, 120, 3800.00, 4700.00),
(52, 24, 22, 10, 100.00, 235.00),
(53, 24, 25, 30, 180.00, 250.00),
(54, 25, 49, 189, 89.00, 127.00);

--
-- Disparadores `detalle_ingreso`
--
DELIMITER $$
CREATE TRIGGER `tr_updStockIngreso` AFTER INSERT ON `detalle_ingreso` FOR EACH ROW BEGIN
UPDATE articulo SET stock=stock + NEW.cantidad
WHERE articulo.idarticulo = NEW.idarticulo;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `iddetalle_venta` int(11) NOT NULL,
  `idventa` int(11) NOT NULL,
  `idarticulo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_venta` decimal(11,2) NOT NULL,
  `descuento` decimal(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `detalle_venta`
--

INSERT INTO `detalle_venta` (`iddetalle_venta`, `idventa`, `idarticulo`, `cantidad`, `precio_venta`, `descuento`) VALUES
(68, 57, 3, 2, 2400.00, 0.00),
(69, 57, 11, 4, 400.00, 0.00),
(70, 58, 5, 10, 550.00, 0.00),
(71, 58, 23, 1, 2100.00, 0.00),
(72, 59, 1, 2, 2800.00, 0.00),
(73, 60, 13, 1, 450.00, 0.00),
(74, 61, 25, 1, 4500.00, 0.00),
(75, 62, 1, 10, 2800.00, 0.00),
(76, 63, 24, 8, 5200.00, 0.00),
(77, 64, 3, 1, 2400.00, 0.00),
(78, 65, 1, 1, 2800.00, 0.00),
(79, 66, 1, 1, 2800.00, 0.00),
(80, 67, 12, 1, 750.00, 0.00),
(81, 68, 12, 1, 750.00, 0.00),
(82, 69, 1, 1, 2800.00, 0.00),
(83, 69, 25, 1, 4500.00, 0.00),
(84, 69, 14, 1, 520.00, 0.00),
(85, 70, 23, 1, 2100.00, 336.00),
(86, 70, 22, 1, 1800.00, 0.00),
(87, 71, 49, 1, 650.00, 162.50);

--
-- Disparadores `detalle_venta`
--
DELIMITER $$
CREATE TRIGGER `tr_auditDetalleVenta` AFTER INSERT ON `detalle_venta` FOR EACH ROW BEGIN
INSERT INTO auditoria (idusuario, tabla_afectada, accion, datos_nuevos, fecha_hora)
VALUES (
(SELECT idusuario FROM venta WHERE idventa = NEW.idventa),
'detalle_venta',
'INSERT',
JSON_OBJECT(
'iddetalle_venta', NEW.iddetalle_venta,
'idventa', NEW.idventa,
'idarticulo', NEW.idarticulo,
'cantidad', NEW.cantidad,
'precio_venta', NEW.precio_venta,
'descuento', NEW.descuento
),
NOW()
);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tr_udpStockVenta` AFTER INSERT ON `detalle_venta` FOR EACH ROW BEGIN
DECLARE new_stock INT;
SET new_stock = (SELECT stock - NEW.cantidad FROM articulo WHERE idarticulo = NEW.idarticulo);
IF new_stock < 0 THEN
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Stock insuficiente para el artículo';
ELSE
UPDATE articulo SET stock = new_stock WHERE idarticulo = NEW.idarticulo;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devolucion`
--

CREATE TABLE `devolucion` (
  `iddevolucion` int(11) NOT NULL,
  `idventa` int(11) NOT NULL,
  `idarticulo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `motivo` varchar(256) NOT NULL,
  `fecha_devolucion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Disparadores `devolucion`
--
DELIMITER $$
CREATE TRIGGER `tr_auditDevolucion` AFTER INSERT ON `devolucion` FOR EACH ROW BEGIN
INSERT INTO auditoria (idusuario, tabla_afectada, accion, datos_nuevos, fecha_hora)
VALUES (
1, -- Asumiendo idusuario 1 (admin) para este ejemplo, ajustar según lógica de tu sistema
'devolucion',
'INSERT',
JSON_OBJECT(
'iddevolucion', NEW.iddevolucion,
'idventa', NEW.idventa,
'idarticulo', NEW.idarticulo,
'cantidad', NEW.cantidad,
'motivo', NEW.motivo,
'fecha_devolucion', NEW.fecha_devolucion
),
NOW()
);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tr_updStockDevolucion` AFTER INSERT ON `devolucion` FOR EACH ROW BEGIN
UPDATE articulo SET stock = stock + NEW.cantidad
WHERE idarticulo = NEW.idarticulo;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingreso`
--

CREATE TABLE `ingreso` (
  `idingreso` int(11) NOT NULL,
  `idproveedor` int(11) NOT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `tipo_comprobante` varchar(20) NOT NULL,
  `serie_comprobante` varchar(7) DEFAULT NULL,
  `num_comprobante` varchar(10) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `impuesto` decimal(4,2) NOT NULL,
  `total_compra` decimal(11,2) NOT NULL,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `ingreso`
--

INSERT INTO `ingreso` (`idingreso`, `idproveedor`, `idusuario`, `tipo_comprobante`, `serie_comprobante`, `num_comprobante`, `fecha_hora`, `impuesto`, `total_compra`, `estado`) VALUES
(18, 29, 1, 'Boleta', 'C.B001', '00001', '2025-07-03 00:00:00', 18.00, 351000.00, 'Aceptado'),
(19, 28, 1, 'Boleta', 'C.B001', '00002', '2025-07-04 00:00:00', 18.00, 11000.00, 'Anulado'),
(20, 35, 1, 'Factura', 'C.F001', '00001', '2025-07-04 00:00:00', 18.00, 9600.00, 'Aceptado'),
(21, 35, 1, 'Ticket', 'C.T001', '00001', '2025-07-04 00:00:00', 18.00, 49400.00, 'Aceptado'),
(22, 29, 1, 'Factura', 'C.F001', '00002', '2025-07-04 00:00:00', 18.00, 154000.00, 'Aceptado'),
(23, 40, 1, 'Factura', 'C.F001', '00003', '2025-07-04 00:00:00', 18.00, 9000.00, 'Aceptado'),
(24, 42, 1, 'Factura', 'C.F001', '00004', '2025-07-04 00:00:00', 18.00, 522400.00, 'Aceptado'),
(25, 30, 1, 'Factura', 'C.F001', '00005', '2025-07-04 00:00:00', 18.00, 16821.00, 'Aceptado');

-- --------------------------------------------------------


--
-- Estructura de tabla para la tabla `permiso`
--

CREATE TABLE `permiso` (
  `idpermiso` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `permiso`
--

INSERT INTO `permiso` (`idpermiso`, `nombre`) VALUES
(1, 'Escritorio'),
(2, 'Almacen'),
(3, 'Compras'),
(4, 'Ventas'),
(5, 'Acceso'),
(6, 'Consulta Compras'),
(7, 'Consulta Ventas'),
(8, 'Promociones'),
(9, 'Devoluciones'),
(10, 'Tienda');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `idpersona` int(11) NOT NULL,
  `tipo_persona` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo_documento` varchar(20) DEFAULT NULL,
  `num_documento` varchar(20) DEFAULT NULL,
  `direccion` varchar(70) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`idpersona`, `tipo_persona`, `nombre`, `tipo_documento`, `num_documento`, `direccion`, `telefono`, `email`) VALUES
(1, 'Cliente', 'Carlos Quispe Mendoza', 'DNI', '72345678', 'Av. Los Ficus 123, Urb. Santa Victoria', '987654321', 'c.quispe.m@gmail.com'),
(2, 'Cliente', 'Ana Lucía Torres Rojas', 'DNI', '78901234', 'Jr. Las Begonias 456, San Isidro', '912345678', 'ana.torres.r@hotmail.com'),
(3, 'Cliente', 'Javier Fernandez Diaz', 'DNI', '71234567', 'Calle Manuel Almenara 789, Surco', '923456789', 'javier.fernandez.d@yahoo.com'),
(4, 'Cliente', 'Sofia Ramirez Vargas', 'DNI', '74567890', 'Urb. Los Pinos Mz. A Lt. 10', '934567890', 'sofia.ramirez.v@outlook.com'),
(5, 'Cliente', 'D Luis´s IRL', 'RUC', '10723456781', 'Av. El Sol 321, Cusco', '945678901', 'luis.garcia.ruc@gmail.com'),
(6, 'Cliente', 'Mariana Castillo Flores', 'DNI', '76543210', 'Calle Bolivar 987, Arequipa', '956789012', 'mariana.castillo.f@gmail.com'),
(7, 'Cliente', 'Ricardo Mendoza Chavez', 'DNI', '73210987', 'Jr. Dos de Mayo 654, Ica', '967890123', 'ricardo.mendoza.c@hotmail.com'),
(8, 'Cliente', 'Valeria Rojas Paredes', 'DNI', '79876543', 'Av. Aviación 2109, San Borja', '978901234', 'valeria.rojas.p@gmail.com'),
(9, 'Cliente', 'Diego Alonso Soto', 'Carnet de Extranjeri', '001234567', 'Av. Larco 789, Miraflores', '989012345', 'diego.alonso.s@outlook.com'),
(10, 'Cliente', 'Camila Vargas Ortiz', 'DNI', '75432109', 'Calle Las Palmeras 333, La Molina', '990123456', 'camila.vargas.o@yahoo.com'),
(11, 'Cliente', 'Jorge Rios Gutierrez', 'DNI', '72109876', 'Urb. San Antonio Mz. F Lt. 21', '901234567', 'jorge.rios.g@gmail.com'),
(12, 'Cliente', 'Luciana Herrera Silva', 'DNI', '77890123', 'Av. Grau 1122, Barranco', '912345670', 'luciana.herrera.s@hotmail.com'),
(13, 'Cliente', 'Mateo Sanchez Lopez', 'DNI', '74321098', 'Jr. de la Unión 555, Cercado de Lima', '923456701', 'mateo.sanchez.l@gmail.com'),
(14, 'Cliente', 'Isabella Morales Cruz', 'DNI', '76789012', 'Calle Los Jazmines 444, Lince', '934567012', 'isabella.morales.c@outlook.com'),
(15, 'Cliente', 'Alegres Sac', 'RUC', '10789012345', 'Av. Salaverry 2233, Jesús María', '945670123', 'andres.nunez.ruc@gmail.com'),
(16, 'Cliente', 'Gabriela Ponce Luna', 'DNI', '71098765', 'Urb. Mariscal Caceres Mz. B Lt. 5', '956701234', 'gabriela.ponce.l@yahoo.com'),
(17, 'Cliente', 'Sebastian Torres Leon', 'DNI', '78765432', 'Jr. Ica 432, Centro Histórico', '967012345', 'sebastian.torres.l@gmail.com'),
(18, 'Cliente', 'Valentina Diaz Peña', 'DNI', '73012345', 'Calle Schell 321, Miraflores', '970123456', 'valentina.diaz.p@hotmail.com'),
(19, 'Cliente', 'Martin Romero Guzman', 'DNI', '75012345', 'Av. Arequipa 4567, Miraflores', '981234567', 'martin.romero.g@gmail.com'),
(20, 'Cliente', 'Daniela Benitez Vega', 'Carnet de Extranjeri', '002345678', 'Calle Porta 111, Miraflores', '992345678', 'daniela.benitez.v@outlook.com'),
(21, 'Cliente', 'Alejandro Cuba Ramos', 'DNI', '72034567', 'Jr. Ocoña 120, Lima', '903456789', 'alejandro.cuba.r@yahoo.com'),
(22, 'Cliente', 'Fernanda Medina Salas', 'DNI', '78045678', 'Av. Primavera 1880, Surco', '914567890', 'fernanda.medina.s@gmail.com'),
(23, 'Cliente', 'Emilio Flores Tapia', 'DNI', '71056789', 'Calle Los Laureles 500, San Isidro', '925678901', 'emilio.flores.t@hotmail.com'),
(24, 'Cliente', 'Laura Pinto Reyes', 'DNI', '74067890', 'Av. Angamos Este 2010, Surquillo', '936789012', 'laura.pinto.r@gmail.com'),
(25, 'Cliente', 'Público General', 'DNI', '00000000', 'Sin dirección', '999999999', 'publico.general@sistema.com'),
(26, 'Proveedor', 'DELTRON S.A.C.', 'RUC', '20100025484', 'Av. Argentina 3093, Callao', '014800800', 'ventas.corporativas@deltron.com.pe'),
(27, 'Proveedor', 'INGRAM MICRO PERÚ S.A.', 'RUC', '20336209539', 'Calle Dean Valdivia 148, San Isidro', '016109500', 'contacto.pe@ingrammicro.com'),
(28, 'Proveedor', 'MAXIMA INTERNACIONAL S.A.', 'RUC', '20101037267', 'Av. Guillermo Dansey 2033, Lima', '013368686', 'info@maxima.com.pe'),
(29, 'Proveedor', 'COMPUDISK S.A.C.', 'RUC', '20504859211', 'Av. Garcilaso de la Vega 1251, Lima', '014247575', 'ventas@compudisk.com.pe'),
(30, 'Proveedor', 'TECH DATA PERÚ S.A.C.', 'RUC', '20552719261', 'Av. Jorge Basadre 345, San Isidro', '016187900', 'consultas.pe@techdata.com'),
(31, 'Proveedor', 'NEXSYS DEL PERÚ S.A.', 'RUC', '20381387693', 'Calle Los Halcones 506, Surquillo', '016191919', 'comercial@nexsys.com.pe'),
(32, 'Proveedor', 'PC LINK S.A.C.', 'RUC', '20506283731', 'Av. Garcilaso de la Vega 1348, Stand 1A-125', '014337000', 'info@pclink.pe'),
(33, 'Proveedor', 'COMPUPALACE S.A.', 'RUC', '20507851611', 'Av. Petit Thouars 5356, Miraflores', '012423336', 'atencion@compupalace.com.pe'),
(34, 'Proveedor', 'OMEGA TECH S.R.L.', 'RUC', '20452319874', 'Parque Industrial de Arequipa, Mz. C Lt. 5', '054281122', 'contacto@omegatech.com.pe'),
(35, 'Proveedor', 'GRUPO INFOTEC E.I.R.L.', 'RUC', '20487563219', 'Av. El Ejército 710, Trujillo', '044297788', 'ventas.trujillo@grupoinfotec.com'),
(36, 'Proveedor', 'SOLUCIONES POS PERU S.A.C.', 'RUC', '20601548752', 'Calle Los Negocios 456, Surquillo', '016805500', 'info@solucionespos.pe'),
(37, 'Proveedor', 'SEGURIDAD TOTAL PERÚ S.A.', 'RUC', '20521478963', 'Av. Tomás Marsano 2832, Miraflores', '014482424', 'proyectos@seguridadtotal.com'),
(38, 'Proveedor', 'LOGISTICORP S.A.', 'RUC', '20498751234', 'Av. Elmer Faucett 2828, Callao', '015751000', 'operaciones@logisticorp.com.pe'),
(39, 'Proveedor', 'CYBER PLAZA LIMA S.A.C.', 'RUC', '20518746325', 'Av. Garcilaso de la Vega 1348, Lima', '013320267', 'administracion@cyberplaza.com.pe'),
(40, 'Proveedor', 'ERGOSISTEMAS S.A.C.', 'RUC', '20508963147', 'Calle Amador Merino Reyna 267, San Isidro', '014421400', 'info@ergosistemas.com'),
(41, 'Proveedor', 'DIGITAL WORLD PERU E.I.R.L.', 'RUC', '20489652147', 'Av. La Marina 2456, San Miguel', '015663322', 'gerencia@digitalworld.com.pe'),
(42, 'Proveedor', 'CONECTIVIDAD GLOBAL S.R.L.', 'RUC', '20451236987', 'Jr. Paruro 1254, Lima', '014278899', 'ventas@conectividadglobal.net'),
(43, 'Proveedor', 'INNOVA TECH SOLUTIONS S.A.C.', 'RUC', '20600458712', 'Calle Dionisio Derteano 184, San Isidro', '017175500', 'proyectos@innovatech.com'),
(44, 'Proveedor', 'DATA CENTER SUPPLY S.A.', 'RUC', '20547896321', 'Av. Canaval y Moreyra 425, San Isidro', '016168888', 'contacto@datasupply.pe'),
(45, 'Proveedor', 'MOBILE PARTS PERU S.A.C.', 'RUC', '20602145874', 'Jr. Huaraz 1550, Breña', '013325599', 'ventas@mobileparts.com.pe'),
(46, 'Proveedor', 'KINGSTON PERU S.R.L.', 'RUC', '20509632145', 'Av. República de Panamá 3591, San Isidro', '012220191', 'soporte.peru@kingston.com'),
(47, 'Proveedor', 'LENOVO PERU S.A.', 'RUC', '20516945874', 'Av. Santo Toribio 115, San Isidro', '080051914', 'ventas.pe@lenovo.com'),
(48, 'Proveedor', 'HP PERU S.R.L.', 'RUC', '20336209580', 'Av. Canaval y Moreyra 380, San Isidro', '016157700', 'atencion.cliente@hp.com'),
(49, 'Proveedor', 'EPSON PERU S.A.', 'RUC', '20338954718', 'Av. Derteano 133, San Isidro', '014180210', 'marketing@epson.com.pe'),
(50, 'Proveedor', 'ASUS PERU S.A.C.', 'RUC', '20552489632', 'Calle Las Orquídeas 585, San Isidro', '017119700', 'info.pe@asus.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo_documento` varchar(20) NOT NULL,
  `num_documento` varchar(20) NOT NULL,
  `direccion` varchar(70) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `cargo` varchar(20) DEFAULT NULL,
  `login` varchar(20) NOT NULL,
  `clave` varchar(64) NOT NULL,
  `imagen` varchar(50) NOT NULL,
  `condicion` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nombre`, `tipo_documento`, `num_documento`, `direccion`, `telefono`, `email`, `fecha_nacimiento`, `cargo`, `login`, `clave`, `imagen`, `condicion`) VALUES
(1, 'Andree Abando Bustamante', 'DNI', '75415202', 'Arenales, en la vía Los Claveles #119', '989568752', 'admin@gmail.com', '2004-02-29', 'Administrador', 'admin', '$2a$12$Xzs39Lir49KmkCweI0WVsOF2mCsUAuTEn/.HPL7AfCVyqS6uPfW0S', '1745599179.jpg', 1),
(2, 'Beatriz Flores Rojas', 'DNI', '41234567', 'Calle Las Camelias 231, San Isidro', '997234567', 'b.flores.adm@empresa.com', '1985-02-20', 'Administrador', 'bflores', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(3, 'Carlos Mendoza Solano', 'RUC', '10423456789', 'Av. Javier Prado Este 1122, La Victoria', '996345678', 'c.mendoza.vta@empresa.com', '1988-11-10', 'Jefe de Ventas', 'cmendoza', '$2a$12$Xzs39Lir49KmkCweI0WVsOF2mCsUAuTEn/.HPL7AfCVyqS6uPfW0S', 'default.png', 1),
(4, 'Diana Quispe Fernandez', 'RUC', '10434567890', 'Jr. Las Esmeraldas 876, Lince', '995456789', 'd.quispe.alm@empresa.com', '1990-07-25', 'Jefe de Almacén', 'dquispe', '$2a$12$Xzs39Lir49KmkCweI0WVsOF2mCsUAuTEn/.HPL7AfCVyqS6uPfW0S', 'default.png', 1),
(5, 'Ernesto Soto Ramirez', 'DNI', '01234567', 'Calle Los Pinos 555, Miraflores', '994567890', 'e.soto.com@empresa.com', '1987-03-30', 'Jefe de Compras', 'esoto', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(6, 'Fernanda Garcia Lopez', 'DNI', '44567890', 'Av. Angamos 2345, Surquillo', '981122334', 'f.garcia.ventas@empresa.com', '1995-01-12', 'Vendedores', 'fgarcia', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(7, 'Gabriel Torres Vega', 'DNI', '45678901', 'Calle Schell 345, Miraflores', '982233445', 'g.torres.ventas@empresa.com', '1994-08-22', 'Vendedores', 'gtorres', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(8, 'Hector Rios Morales', 'DNI', '46789012', 'Jr. de la Union 890, Lima', '983344556', 'h.rios.ventas@empresa.com', '1996-04-18', 'Vendedores', 'hrios', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(9, 'Irene Salazar Castillo', 'RUC', '10478901234', 'Av. Arequipa 4500, Miraflores', '984455667', 'i.salazar.ventas@empresa.com', '1993-12-01', 'Vendedores', 'isalazar', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(10, 'Javier Nuñez Paredes', 'RUC', '10489012345', 'Calle Berlin 567, Miraflores', '985566778', 'j.nunez.ventas@empresa.com', '1992-06-14', 'Vendedores', 'jnunez', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(11, 'Karina Ponce Diaz', 'RUC', '10490123456', 'Av. Benavides 3344, Surco', '986677889', 'k.ponce.ventas@empresa.com', '1997-02-05', 'Vendedores', 'kponce', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(12, 'Leonardo Vega Cruz', 'Carnet de Extranjeri', '002345678', 'Av. del Ejercito 1180, Magdalena', '987788990', 'l.vega.ventas@empresa.com', '1991-10-28', 'Vendedores', 'lvega', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(13, 'Monica Chavez Guzman', 'Carnet de Extranjeri', '003456789', 'Jr. Carabaya 543, Lima', '988899001', 'm.chavez.ventas@empresa.com', '1998-05-15', 'Vendedores', 'mchavez', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(14, 'Nicolas Benitez Salas', 'DNI', '47890123', 'Calle Alcanfores 123, Miraflores', '989900112', 'n.benitez.ventas@empresa.com', '1999-01-20', 'Vendedores', 'nbenitez', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(15, 'Olivia Ramos Cuba', 'DNI', '48901234', 'Av. Comandante Espinar 789, Miraflores', '990011223', 'o.ramos.ventas@empresa.com', '2000-09-03', 'Vendedores', 'oramos', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(16, 'Pedro Medina Tapia', 'DNI', '49012345', 'Urb. Los Jardines Mz C Lt. 14, S.J.L.', '971122334', 'p.medina.almacen@empresa.com', '1995-03-11', 'Asistente de Almacén', 'pmedina', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(17, 'Quintin Reyes Pinto', 'DNI', '50123456', 'Av. Central 456, Villa El Salvador', '972233445', 'q.reyes.almacen@empresa.com', '1994-06-21', 'Asistente de Almacén', 'qreyes', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(18, 'Raul Guzman Leon', 'RUC', '10512345678', 'Calle Las Magnolias 111, San Borja', '973344556', 'r.guzman.almacen@empresa.com', '1993-11-02', 'Asistente de Almacén', 'rguzman', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(19, 'Sandra Tapia Flores', 'RUC', '10523456789', 'Av. San Luis 2233, San Borja', '974455667', 's.tapia.almacen@empresa.com', '1996-08-13', 'Asistente de Almacén', 'stapia', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(20, 'Tomas Luna Castro', 'Carnet de Extranjeri', '004567890', 'Jr. Ayacucho 777, Surco', '975566778', 't.luna.almacen@empresa.com', '1992-04-24', 'Asistente de Almacén', 'tluna', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(21, 'Ursula Peña Romero', 'DNI', '51234567', 'Av. La Marina 3210, San Miguel', '961122334', 'u.pena.caja@empresa.com', '2001-02-15', 'Caja', 'upena', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(22, 'Victor Vega Salas', 'DNI', '52345678', 'Jr. Huiracocha 1456, Jesús María', '962233445', 'v.vega.caja@empresa.com', '2002-07-25', 'Caja', 'vvega', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(23, 'Wendy Castro Nuñez', 'DNI', '53456789', 'Av. Brasil 1890, Pueblo Libre', '963344556', 'w.castro.caja@empresa.com', '2000-12-05', 'Caja', 'wcastro', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(24, 'Ximena Diaz Morales', 'RUC', '10534567890', 'Calle Gral. Garzón 987, Jesús María', '964455667', 'x.diaz.caja@empresa.com', '1999-09-19', 'Caja', 'xdiaz', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(25, 'Yanet Silva Ponce', 'RUC', '10545678901', 'Av. Arnaldo Marquez 1234, Jesús María', '965566778', 'y.silva.caja@empresa.com', '1998-04-10', 'Caja', 'ysilva', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(26, 'Zoe Cruz Benitez', 'Carnet de Extranjeri', '005678901', 'Av. Pardo de Zela 456, Lince', '966677889', 'z.cruz.caja@empresa.com', '1997-11-20', 'Caja', 'zcruz', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(27, 'Aaron Cuba Herrera', 'Carnet de Extranjeri', '006789012', 'Av. Arenales 2211, Lince', '967788990', 'a.cuba.caja@empresa.com', '1996-06-01', 'Caja', 'acuba', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(28, 'Bruno Ramos Sanchez', 'DNI', '54567890', 'Paseo de la República 3344, San Isidro', '968899001', 'b.ramos.caja@empresa.com', '2003-01-30', 'Caja', 'bramos', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(29, 'Cesar Lopez Pinto', 'DNI', '55678901', 'Av. Garcilaso de la Vega 1330, Lima', '951122334', 'c.lopez.soporte@empresa.com', '1998-08-18', 'Soporte Técnico', 'clopez', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(30, 'Daniela Medina Reyes', 'DNI', '56789012', 'Av. Bolivia 180, Lima', '952233445', 'd.medina.soporte@empresa.com', '1997-03-22', 'Soporte Técnico', 'dmedina', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(31, 'Esteban Guzman Flores', 'DNI', '57890123', 'Jr. Camaná 991, Lima', '953344556', 'e.guzman.soporte@empresa.com', '1996-10-14', 'Soporte Técnico', 'eguzman', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(32, 'Fabian Leon Tapia', 'RUC', '10556789012', 'Av. Uruguay 320, Lima', '954455667', 'f.leon.soporte@empresa.com', '1995-05-05', 'Soporte Técnico', 'fleon', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(33, 'Gisela Castro Salas', 'RUC', '10567890123', 'Av. Wilson 1180, Lima', '955566778', 'g.castro.soporte@empresa.com', '1994-01-25', 'Soporte Técnico', 'gcastro', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(34, 'Hugo Nuñez Morales', 'Carnet de Extranjeri', '007890123', 'Jr. Washington 1450, Lima', '956677889', 'h.nunez.soporte@empresa.com', '1993-08-08', 'Soporte Técnico', 'hnunez', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(35, 'Ivan Paredes Rojas', 'Carnet de Extranjeri', '008901234', 'Av. Roosevelt 6345, Lima', '957788990', 'i.paredes.soporte@empresa.com', '1992-02-18', 'Soporte Técnico', 'iparedes', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(36, 'Jimena Vargas Diaz', 'DNI', '58901234', 'Av. Emancipación 560, Lima', '958899001', 'j.vargas.soporte@empresa.com', '2004-04-04', 'Soporte Técnico', 'jvargas', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(37, 'Kiara Soto Castillo', 'DNI', '59012345', 'Av. Alfonso Ugarte 1010, Breña', '941122334', 'k.soto.atc@empresa.com', '2001-11-11', 'Atención al Cliente', 'ksoto', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(38, 'Luis Ramirez Ponce', 'DNI', '60123456', 'Av. Tingo Maria 789, Breña', '942233445', 'l.ramirez.atc@empresa.com', '2002-06-16', 'Atención al Cliente', 'lramirez', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(39, 'Marta Fernandez Leon', 'DNI', '61234567', 'Jr. Huaraz 1876, Breña', '943344556', 'm.fernandez.atc@empresa.com', '2003-03-03', 'Atención al Cliente', 'mfernandez', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(40, 'Natalia Garcia Guzman', 'RUC', '10623456789', 'Av. Venezuela 1520, Breña', '944455667', 'n.garcia.atc@empresa.com', '1999-07-07', 'Atención al Cliente', 'ngarcia', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(41, 'Oscar Torres Tapia', 'RUC', '10634567890', 'Jr. Zorritos 899, Breña', '945566778', 'o.torres.atc@empresa.com', '1998-02-02', 'Atención al Cliente', 'otorres', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(42, 'Patricia Rios Luna', 'RUC', '10645678901', 'Av. Arica 654, Breña', '946677889', 'p.rios.atc@empresa.com', '1997-10-10', 'Atención al Cliente', 'prios', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(43, 'Renzo Salazar Benitez', 'RUC', '10656789012', 'Jr. Aguarico 330, Breña', '947788990', 'r.salazar.atc@empresa.com', '1996-05-05', 'Atención al Cliente', 'rsalazar', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(44, 'Silvia Nuñez Cuba', 'RUC', '10667890123', 'Calle Napo 150, Breña', '948899001', 's.nunez.atc@empresa.com', '1995-12-12', 'Atención al Cliente', 'snunez', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(45, 'Tania Paredes Ramos', 'Carnet de Extranjeri', '009012345', 'Av. Bolivia 987, Breña', '949900112', 't.paredes.atc@empresa.com', '1994-08-08', 'Atención al Cliente', 'tparedes', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(46, 'Ugo Vargas Medina', 'Carnet de Extranjeri', '010123456', 'Jr. Iquique 456, Breña', '950011223', 'u.vargas.atc@empresa.com', '1993-01-01', 'Atención al Cliente', 'uvargas', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(47, 'Valeria Ponce Pinto', 'Carnet de Extranjeri', '011234567', 'Av. Restauracion 321, Breña', '951122334', 'v.ponce.atc@empresa.com', '1992-04-04', 'Atención al Cliente', 'vponce', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(48, 'Walter Vega Guzman', 'Carnet de Extranjeri', '012345678', 'Jr. Varela 123, Breña', '952233445', 'w.vega.compras@empresa.com', '1991-09-09', 'Jefe de Compras', 'wvega', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(49, 'Xenia Castro Leon', 'Carnet de Extranjeri', '013456789', 'Av. Socialismo 444, Ate', '953344556', 'x.castro.ventas@empresa.com', '1990-11-11', 'Jefe de Ventas', 'xcastro', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1),
(50, 'Yuri Diaz Flores', 'DNI', '62345678', 'Carretera Central Km 5.5, Ate', '954455667', 'y.diaz.almacen@empresa.com', '1989-07-07', 'Jefe de Almacén', 'ydiaz', '$2a$12$6qeiOo6cWxiHj74yf.I0QeBMcMnQOZoZ1wfxb1PL.iJO1b/SBb4tG', 'default.png', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_permiso`
--

CREATE TABLE `usuario_permiso` (
  `idusuario_permiso` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `idpermiso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuario_permiso`
--

INSERT INTO `usuario_permiso` (`idusuario_permiso`, `idusuario`, `idpermiso`) VALUES
(195, 7, 4),
(196, 7, 7),
(197, 7, 9),
(198, 7, 10),
(199, 8, 4),
(200, 8, 7),
(201, 8, 9),
(202, 8, 10),
(203, 9, 4),
(204, 9, 7),
(205, 9, 9),
(206, 9, 10),
(207, 10, 4),
(208, 10, 7),
(209, 10, 9),
(210, 10, 10),
(211, 11, 4),
(212, 11, 7),
(213, 11, 9),
(214, 11, 10),
(215, 12, 4),
(216, 12, 7),
(217, 12, 9),
(218, 12, 10),
(219, 13, 4),
(220, 13, 7),
(221, 13, 9),
(222, 13, 10),
(223, 14, 4),
(224, 14, 7),
(225, 14, 9),
(226, 14, 10),
(227, 15, 4),
(228, 15, 7),
(229, 15, 9),
(230, 15, 10),
(234, 17, 2),
(235, 17, 6),
(236, 17, 9),
(237, 18, 2),
(238, 18, 6),
(239, 18, 9),
(240, 19, 2),
(241, 19, 6),
(242, 19, 9),
(243, 20, 2),
(244, 20, 6),
(245, 20, 9),
(246, 21, 4),
(247, 21, 7),
(248, 21, 10),
(249, 22, 4),
(250, 22, 7),
(251, 22, 10),
(252, 23, 4),
(253, 23, 7),
(254, 23, 10),
(255, 24, 4),
(256, 24, 7),
(257, 24, 10),
(258, 25, 4),
(259, 25, 7),
(260, 25, 10),
(261, 26, 4),
(262, 26, 7),
(263, 26, 10),
(264, 27, 4),
(265, 27, 7),
(266, 27, 10),
(267, 28, 4),
(268, 28, 7),
(269, 28, 10),
(270, 29, 2),
(271, 29, 7),
(272, 30, 2),
(273, 30, 7),
(274, 31, 2),
(275, 31, 7),
(276, 32, 2),
(277, 32, 7),
(278, 33, 2),
(279, 33, 7),
(280, 34, 2),
(281, 34, 7),
(282, 35, 2),
(283, 35, 7),
(284, 36, 2),
(285, 36, 7),
(286, 37, 7),
(287, 37, 9),
(288, 37, 10),
(289, 38, 7),
(290, 38, 9),
(291, 38, 10),
(292, 39, 7),
(293, 39, 9),
(294, 39, 10),
(295, 40, 7),
(296, 40, 9),
(297, 40, 10),
(298, 41, 7),
(299, 41, 9),
(300, 41, 10),
(301, 42, 7),
(302, 42, 9),
(303, 42, 10),
(304, 43, 7),
(305, 43, 9),
(306, 43, 10),
(307, 44, 7),
(308, 44, 9),
(309, 44, 10),
(310, 45, 7),
(311, 45, 9),
(312, 45, 10),
(313, 46, 7),
(314, 46, 9),
(315, 46, 10),
(316, 47, 7),
(317, 47, 9),
(318, 47, 10),
(319, 48, 1),
(320, 48, 2),
(321, 48, 3),
(322, 48, 6),
(323, 49, 1),
(324, 49, 4),
(325, 49, 6),
(326, 49, 7),
(327, 49, 8),
(328, 49, 9),
(329, 49, 10),
(330, 50, 1),
(331, 50, 2),
(332, 50, 3),
(333, 50, 6),
(334, 50, 9),
(335, 1, 1),
(336, 1, 2),
(337, 1, 3),
(338, 1, 4),
(339, 1, 5),
(340, 1, 6),
(341, 1, 7),
(342, 1, 8),
(343, 1, 9),
(344, 1, 10),
(355, 3, 1),
(356, 3, 2),
(357, 3, 4),
(358, 3, 7),
(359, 3, 8),
(360, 3, 10),
(361, 2, 1),
(362, 2, 2),
(363, 2, 3),
(364, 2, 4),
(365, 2, 5),
(366, 2, 6),
(367, 2, 7),
(368, 2, 8),
(369, 2, 9),
(370, 2, 10),
(371, 4, 1),
(372, 4, 2),
(373, 4, 3),
(374, 4, 6),
(375, 4, 9),
(376, 5, 1),
(377, 5, 2),
(378, 5, 3),
(379, 5, 6),
(380, 6, 4),
(381, 6, 7),
(382, 6, 9),
(383, 6, 10),
(384, 16, 2),
(385, 16, 6),
(386, 16, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `idventa` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `tipo_comprobante` varchar(20) NOT NULL,
  `serie_comprobante` varchar(7) DEFAULT NULL,
  `num_comprobante` varchar(10) NOT NULL,
  `fecha_hora` datetime NOT NULL DEFAULT current_timestamp(),
  `impuesto` decimal(4,2) DEFAULT NULL,
  `total_venta` decimal(11,2) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`idventa`, `idcliente`, `idusuario`, `tipo_comprobante`, `serie_comprobante`, `num_comprobante`, `fecha_hora`, `impuesto`, `total_venta`, `estado`) VALUES
(57, 3, 1, 'Factura', 'F.V001', '000001', '2025-07-03 20:22:00', 18.00, 6400.00, 'Aceptado'),
(58, 12, 1, 'Boleta', 'B.V001', '000001', '2025-07-03 20:30:00', 18.00, 7600.00, 'Aceptado'),
(59, 8, 1, 'Ticket', 'T.V001', '000001', '2025-07-03 20:31:00', 18.00, 5600.00, 'Aceptado'),
(60, 1, 1, 'Boleta', 'B.V001', '000002', '2025-07-03 20:33:00', 18.00, 450.00, 'Anulado'),
(61, 21, 1, 'Factura', 'F.V001', '000002', '2025-07-03 20:39:00', 18.00, 4500.00, 'Aceptado'),
(62, 25, 1, 'Boleta', 'B.V001', '000003', '2025-07-03 20:48:00', 18.00, 28000.00, 'Aceptado'),
(63, 25, 1, 'Ticket', 'T.V001', '000002', '2025-07-03 20:49:00', 18.00, 41600.00, 'Aceptado'),
(64, 9, 1, 'Factura', 'FV001', '000001', '2025-07-03 20:59:00', 18.00, 2400.00, 'Aceptado'),
(65, 14, 1, 'Boleta', 'BV001', '000001', '2025-07-03 21:00:00', 18.00, 2800.00, 'Aceptado'),
(66, 20, 1, 'Ticket', 'TV001', '000001', '2025-07-03 21:01:00', 18.00, 2800.00, 'Aceptado'),
(67, 15, 1, 'Boleta', 'BV001', '000002', '2025-07-03 21:03:00', 18.00, 750.00, 'Anulado'),
(68, 25, 1, 'Boleta', 'BV001', '000003', '2025-07-03 21:04:00', 18.00, 750.00, 'Aceptado'),
(69, 25, 1, 'Factura', 'FV001', '000002', '2025-07-03 21:05:00', 18.00, 7820.00, 'Aceptado'),
(70, 3, 1, 'Factura', 'FV001', '000003', '2025-07-03 23:16:00', 18.00, 3564.00, 'Aceptado'),
(71, 25, 1, 'Ticket', 'TV001', '000002', '2025-07-04 10:23:00', 18.00, 487.50, 'Aceptado');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`idarticulo`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  ADD KEY `fk_articulo_categoria_idx` (`idcategoria`);

--
-- Indices de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD PRIMARY KEY (`idauditoria`),
  ADD KEY `fk_auditoria_usuario` (`idusuario`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idcategoria`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

--
-- Indices de la tabla `detalle_ingreso`
--
ALTER TABLE `detalle_ingreso`
  ADD PRIMARY KEY (`iddetalle_ingreso`),
  ADD KEY `fk_detalle_ingreso_idx` (`idingreso`),
  ADD KEY `fk_detalle_articulo_idx` (`idarticulo`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`iddetalle_venta`),
  ADD KEY `fk_detalle_venta_venta_idx` (`idventa`),
  ADD KEY `fk_detalle_venta_articulo_idx` (`idarticulo`);

--
-- Indices de la tabla `devolucion`
--
ALTER TABLE `devolucion`
  ADD PRIMARY KEY (`iddevolucion`),
  ADD KEY `fk_devolucion_venta` (`idventa`),
  ADD KEY `fk_devolucion_articulo` (`idarticulo`);

--
-- Indices de la tabla `ingreso`
--
ALTER TABLE `ingreso`
  ADD PRIMARY KEY (`idingreso`),
  ADD KEY `fk_ingreso_persona_idx` (`idproveedor`),
  ADD KEY `fk_ingreso_usuario_idx` (`idusuario`);

--
-- Indices de la tabla `metas_compras`
--

ALTER TABLE `permiso`
  ADD PRIMARY KEY (`idpermiso`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`idpersona`);


-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD UNIQUE KEY `login_UNIQUE` (`login`);

--
-- Indices de la tabla `usuario_permiso`
--
ALTER TABLE `usuario_permiso`
  ADD PRIMARY KEY (`idusuario_permiso`),
  ADD KEY `fk_u_permiso_usuario_idx` (`idusuario`),
  ADD KEY `fk_usuario_permiso_idx` (`idpermiso`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`idventa`),
  ADD KEY `fk_venta_persona_idx` (`idcliente`),
  ADD KEY `fk_venta_usuario_idx` (`idusuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `idarticulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  MODIFY `idauditoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idcategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `detalle_ingreso`
--
ALTER TABLE `detalle_ingreso`
  MODIFY `iddetalle_ingreso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `iddetalle_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- AUTO_INCREMENT de la tabla `devolucion`
--
ALTER TABLE `devolucion`
  MODIFY `iddevolucion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ingreso`
--
ALTER TABLE `ingreso`
  MODIFY `idingreso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `permiso`
--
ALTER TABLE `permiso`
  MODIFY `idpermiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `idpersona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `usuario_permiso`
--
ALTER TABLE `usuario_permiso`
  MODIFY `idusuario_permiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=387;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `idventa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD CONSTRAINT `fk_articulo_categoria` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`);

--
-- Filtros para la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD CONSTRAINT `fk_auditoria_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`);

--
-- Filtros para la tabla `detalle_ingreso`
--
ALTER TABLE `detalle_ingreso`
  ADD CONSTRAINT `fk_detalle_articulo` FOREIGN KEY (`idarticulo`) REFERENCES `articulo` (`idarticulo`),
  ADD CONSTRAINT `fk_detalle_ingreso` FOREIGN KEY (`idingreso`) REFERENCES `ingreso` (`idingreso`);

--
-- Filtros para la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `fk_detalle_venta_articulo` FOREIGN KEY (`idarticulo`) REFERENCES `articulo` (`idarticulo`),
  ADD CONSTRAINT `fk_detalle_venta_venta` FOREIGN KEY (`idventa`) REFERENCES `venta` (`idventa`);

--
-- Filtros para la tabla `devolucion`
--
ALTER TABLE `devolucion`
  ADD CONSTRAINT `fk_devolucion_articulo` FOREIGN KEY (`idarticulo`) REFERENCES `articulo` (`idarticulo`),
  ADD CONSTRAINT `fk_devolucion_venta` FOREIGN KEY (`idventa`) REFERENCES `venta` (`idventa`);

--
-- Filtros para la tabla `ingreso`
--
ALTER TABLE `ingreso`
  ADD CONSTRAINT `fk_ingreso_persona` FOREIGN KEY (`idproveedor`) REFERENCES `persona` (`idpersona`),
  ADD CONSTRAINT `fk_ingreso_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`);


--
-- Filtros para la tabla `usuario_permiso`
--
ALTER TABLE `usuario_permiso`
  ADD CONSTRAINT `fk_u_permiso_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`),
  ADD CONSTRAINT `fk_usuario_permiso` FOREIGN KEY (`idpermiso`) REFERENCES `permiso` (`idpermiso`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `fk_venta_persona` FOREIGN KEY (`idcliente`) REFERENCES `persona` (`idpersona`),
  ADD CONSTRAINT `fk_venta_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
