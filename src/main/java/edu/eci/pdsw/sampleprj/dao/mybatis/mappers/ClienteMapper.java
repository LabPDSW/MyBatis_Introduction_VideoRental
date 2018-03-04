package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Cliente;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    default Cliente consultarCliente(Integer id){
        return consultarClienteGeneral(id).get(0);
    }
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("idi")int id, 
            @Param("idcli") int idit, 
            @Param("fini") Date fechainicio,
            @Param("ffin") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */

    default List<Cliente> consultarClientes(){
        return consultarClienteGeneral(null);
    }
    
    public List<Cliente> consultarClienteGeneral(@Param("idcli") Integer id);
    
}
