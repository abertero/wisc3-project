package cl.wisc3.web.services.crud;

import cl.wisc3.web.beans.crud.CrudEdit;
import cl.wisc3.web.beans.crud.CrudView;

import java.util.List;

public interface CrudBaseEntity {

    CrudEdit getCrudEdit(String altKey);

    CrudView getCrudView(String altKey);

    List<CrudView> getList();
}
