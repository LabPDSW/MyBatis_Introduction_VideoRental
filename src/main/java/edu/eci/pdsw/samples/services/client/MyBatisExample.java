/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.services.client;

import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        //Crear el mapper y usarlo: 
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        List<Cliente> lista = cm.consultarClientes();
        int documetoClinte = 2104835;
        Cliente cliente = cm.consultarCliente(documetoClinte);
        for (Cliente c : lista) {
            System.out.println(c);
        }
//        cm.agregarItemRentadoACliente(12345, 2104835, java.sql.Date.valueOf("2018-03-10"), java.sql.Date.valueOf("2018-03-20")); //inserta ItemRentado{id=1026585724, item=null, fechainiciorenta=2017-09-12, fechafinrenta=2017-09-12}]}
        System.out.println("-------------------------------\nConsultando: " + documetoClinte + "\n" + cliente);
        System.out.println("-------------------------------\nConsulta de items:");
        int idItem = 7;
        List<Item> items = im.consultarItems();
        Item item = im.consultarItem(idItem);
        for (Item i : items) {
            System.out.println(i);
        }
        System.out.println("-------------------------------\nConsultando item: " + idItem + "\n" + item);
        sqlss.commit();
        sqlss.close();
//        System.exit(0);

    }

}
