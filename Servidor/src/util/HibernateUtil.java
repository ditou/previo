package util;

import org.h2.tools.Console;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.ArticuloEntity;
import entity.ClienteEntity;
import entity.ClienteObservacionEntity;
import entity.CompraEntity;
import entity.CompraItemEntity;
import entity.CuentaCorrienteEntity;
import entity.CuentaCorrienteItemEntity;
import entity.FacturaEntity;
import entity.FacturaItemEntity;
import entity.LoteEntity;
import entity.MovimientoStockEntity;
import entity.OrdenCompraEntity;
import entity.PagoEntity;
import entity.PedidoEntity;
import entity.PedidoItemEntity;
import entity.RemitoEntity;
import entity.RemitoItemEntity;
import entity.UbicacionEntity;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	static {
		try {
			Configuration config = new Configuration();
			config.addAnnotatedClass(ArticuloEntity.class);
			config.addAnnotatedClass(ClienteEntity.class);
			config.addAnnotatedClass(ClienteObservacionEntity.class);
			config.addAnnotatedClass(CuentaCorrienteEntity.class);
			config.addAnnotatedClass(CuentaCorrienteItemEntity.class);
			config.addAnnotatedClass(FacturaEntity.class);
			config.addAnnotatedClass(FacturaItemEntity.class);
			config.addAnnotatedClass(LoteEntity.class);
			config.addAnnotatedClass(OrdenCompraEntity.class);
			config.addAnnotatedClass(PagoEntity.class);
			config.addAnnotatedClass(PedidoEntity.class);
			config.addAnnotatedClass(PedidoItemEntity.class);
			config.addAnnotatedClass(RemitoEntity.class);
			config.addAnnotatedClass(RemitoItemEntity.class);
			config.addAnnotatedClass(UbicacionEntity.class);
			config.addAnnotatedClass(MovimientoStockEntity.class);
			config.addAnnotatedClass(CompraEntity.class);
			config.addAnnotatedClass(CompraItemEntity.class);
			//new Console().runTool(); Esto solo sirve para mostrar o abrir el browser de entidades
			//org.h2.tools.Server web = org.h2.tools.Server.createTcpServer();
			org.h2.tools.Server web = org.h2.tools.Server.createWebServer();
			web.start();
			sessionFactory = config.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Initial SessionFactory creation failed." + e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
