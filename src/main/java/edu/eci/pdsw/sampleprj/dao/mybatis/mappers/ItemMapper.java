package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.Item;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    default List<Item> consultarItems(){
        return consultarItemGeneral(null);
    }
    
    default Item consultarItem(int id){
        return consultarItemGeneral(id).get(0);
    }
    
    public List<Item> consultarItemGeneral(@Param("iid") Integer id);
    
    public void insertarItem(@Param("it") Item it);

        
}
