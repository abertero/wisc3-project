package cl.wisc3.web.services.crud;

import cl.wisc3.model.child.ChildLevel;
import cl.wisc3.web.beans.crud.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrudChildLevel implements CrudBaseEntity {
    @Override
    public CrudEdit getCrudEdit(String altKey) {
        if (StringUtils.isNotBlank(altKey)) {
            return getCrudEdit(ChildLevel.findByAltKey(altKey));
        }
        return getCrudEdit(new ChildLevel());
    }

    @Override
    public CrudView getCrudView(String altKey) {
        if (StringUtils.isNotBlank(altKey)) {
            return getCrudView(ChildLevel.findByAltKey(altKey));
        }
        return getCrudView(new ChildLevel());
    }

    @Override
    public List<CrudView> getList() {
        List<CrudView> cruds = new ArrayList<>();
        for (ChildLevel childLevel : ChildLevel.findAll()) {
            cruds.add(getCrudView(childLevel));
        }
        return cruds;
    }

    @Override
    public void save(CrudEdit crudEdit) {
        ChildLevel definition = new ChildLevel();
        if (StringUtils.isNotBlank(crudEdit.getAltKey())) {
            definition = ChildLevel.findByAltKey(crudEdit.getAltKey());
        }
        definition.setEntityOrder(NumberUtils.toLong(crudEdit.getValue("entityOrder"), 1000));
        definition.setName(crudEdit.getValue("name"));
        definition.setDescription(crudEdit.getValue("description"));
        definition.setDaysStart(NumberUtils.toInt(crudEdit.getValue("daysStart")));
        definition.setMonthsStart(NumberUtils.toInt(crudEdit.getValue("monthsStart")));
        definition.setYearsStart(NumberUtils.toInt(crudEdit.getValue("yearsStart")));
        definition.setDaysEnd(NumberUtils.toInt(crudEdit.getValue("daysEnd")));
        definition.setMonthsEnd(NumberUtils.toInt(crudEdit.getValue("monthsEnd")));
        definition.setYearsEnd(NumberUtils.toInt(crudEdit.getValue("yearsEnd")));
        definition.save();
    }

    private CrudEdit getCrudEdit(ChildLevel childLevel) {
        CrudType type = CrudType.CHILD_LEVEL;
        CrudEdit crudEdit = new CrudEdit(childLevel.getAltKey(), type);
        crudEdit.addRow(new CrudEditRow(type, "id", childLevel.getId() != null ? String.valueOf(childLevel.getId()) : "", CrudEditType.READONLY));
        crudEdit.addRow(new CrudEditRow(type, "altKey", childLevel.getAltKey(), CrudEditType.READONLY));
        crudEdit.addRow(new CrudEditRow(type, "entityOrder", childLevel.getEntityOrder() != null ? String.valueOf(childLevel.getEntityOrder()) : "", CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "name", childLevel.getName(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "description", childLevel.getDescription(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "daysStart", String.valueOf(childLevel.getDaysStart()), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "monthsStart", String.valueOf(childLevel.getMonthsStart()), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "yearsStart", String.valueOf(childLevel.getYearsStart()), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "daysEnd", String.valueOf(childLevel.getDaysEnd()), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "monthsEnd", String.valueOf(childLevel.getMonthsEnd()), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "yearsEnd", String.valueOf(childLevel.getYearsEnd()), CrudEditType.TEXT));
        return crudEdit;
    }

    private CrudView getCrudView(ChildLevel childLevel) {
        CrudView crudView = new CrudView(childLevel.getAltKey());
        crudView.getAttributeValue().put("crud.childlevel.displayName.id", childLevel.getId() != null ? String.valueOf(childLevel.getId()) : "");
        crudView.getAttributeValue().put("crud.childlevel.displayName.altKey", childLevel.getAltKey());
        crudView.getAttributeValue().put("crud.childlevel.displayName.entityOrder", childLevel.getEntityOrder() != null ? String.valueOf(childLevel.getEntityOrder()) : "");
        crudView.getAttributeValue().put("crud.childlevel.displayName.name", childLevel.getName());
        crudView.getAttributeValue().put("crud.childlevel.displayName.description", childLevel.getDescription());
        crudView.getAttributeValue().put("crud.childlevel.displayName.daysStart", String.valueOf(childLevel.getDaysStart()));
        crudView.getAttributeValue().put("crud.childlevel.displayName.monthsStart", String.valueOf(childLevel.getMonthsStart()));
        crudView.getAttributeValue().put("crud.childlevel.displayName.yearsStart", String.valueOf(childLevel.getYearsStart()));
        crudView.getAttributeValue().put("crud.childlevel.displayName.daysEnd", String.valueOf(childLevel.getDaysEnd()));
        crudView.getAttributeValue().put("crud.childlevel.displayName.monthsEnd", String.valueOf(childLevel.getMonthsEnd()));
        crudView.getAttributeValue().put("crud.childlevel.displayName.yearsEnd", String.valueOf(childLevel.getYearsEnd()));
        return crudView;
    }

}
