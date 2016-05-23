package cl.wisc3.web.services.crud;

import cl.wisc3.model.child.ChildInfo;
import cl.wisc3.web.beans.crud.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrudChildInfo implements CrudBaseEntity {
    @Override
    public CrudEdit getCrudEdit(String altKey) {
        if (StringUtils.isNotBlank(altKey)) {
            return getCrudEdit(ChildInfo.findByAltKey(altKey));
        }
        return getCrudEdit(new ChildInfo());
    }

    @Override
    public CrudView getCrudView(String altKey) {
        if (StringUtils.isNotBlank(altKey)) {
            return getCrudView(ChildInfo.findByAltKey(altKey));
        }
        return getCrudView(new ChildInfo());
    }

    @Override
    public List<CrudView> getList() {
        List<CrudView> cruds = new ArrayList<>();
        for (ChildInfo childInfo : ChildInfo.findAll()) {
            cruds.add(getCrudView(childInfo));
        }
        return cruds;
    }

    @Override
    public void save(CrudEdit crudEdit) {
        ChildInfo childLevel = new ChildInfo();
        if (StringUtils.isNotBlank(crudEdit.getAltKey())) {
            childLevel = ChildInfo.findByAltKey(crudEdit.getAltKey());
        }
        childLevel.setEntityOrder(NumberUtils.toLong(crudEdit.getValue("entityOrder"), 1000));
        childLevel.setFirstName(crudEdit.getValue("firstName"));
        childLevel.setLastName(crudEdit.getValue("lastName"));
        childLevel.setFatherName(crudEdit.getValue("fatherName"));
        childLevel.setMotherName(crudEdit.getValue("motherName"));
        childLevel.setDescription(crudEdit.getValue("description"));
        childLevel.setBirthDay(NumberUtils.toInt(crudEdit.getValue("birthDay")));
        childLevel.setBirthMonth(NumberUtils.toInt(crudEdit.getValue("birthMonth")));
        childLevel.setBirthYear(NumberUtils.toInt(crudEdit.getValue("birthYear")));
        childLevel.save();
    }

    private CrudEdit getCrudEdit(ChildInfo childInfo) {
        CrudType type = CrudType.CHILD_INFO;
        CrudEdit crudEdit = new CrudEdit(childInfo.getAltKey(), type);
        crudEdit.addRow(new CrudEditRow(type, "id", childInfo.getId() != null ? String.valueOf(childInfo.getId()) : "", CrudEditType.READONLY));
        crudEdit.addRow(new CrudEditRow(type, "altKey", childInfo.getAltKey(), CrudEditType.READONLY));
        crudEdit.addRow(new CrudEditRow(type, "entityOrder", childInfo.getEntityOrder() != null ? String.valueOf(childInfo.getEntityOrder()) : "", CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "firstName", childInfo.getFirstName(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "lastName", childInfo.getLastName(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "fatherName", childInfo.getFatherName(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "motherName", childInfo.getMotherName(), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "description", childInfo.getDescription(), CrudEditType.TEXTAREA));
        crudEdit.addRow(new CrudEditRow(type, "birthDay", String.valueOf(childInfo.getBirthDay()), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "birthMonth", String.valueOf(childInfo.getBirthMonth()), CrudEditType.TEXT));
        crudEdit.addRow(new CrudEditRow(type, "birthYear", String.valueOf(childInfo.getBirthYear()), CrudEditType.TEXT));
        return crudEdit;
    }

    private CrudView getCrudView(ChildInfo childInfo) {
        CrudView crudView = new CrudView(childInfo.getAltKey());
        crudView.getAttributeValue().put("crud.childinfo.displayName.id", childInfo.getId() != null ? String.valueOf(childInfo.getId()) : "");
        crudView.getAttributeValue().put("crud.childinfo.displayName.altKey", childInfo.getAltKey());
        crudView.getAttributeValue().put("crud.childinfo.displayName.entityOrder", childInfo.getEntityOrder() != null ? String.valueOf(childInfo.getEntityOrder()) : "");
        crudView.getAttributeValue().put("crud.childinfo.displayName.firstName", childInfo.getFirstName());
        crudView.getAttributeValue().put("crud.childinfo.displayName.lastName", childInfo.getLastName());
        crudView.getAttributeValue().put("crud.childinfo.displayName.fatherName", childInfo.getFatherName());
        crudView.getAttributeValue().put("crud.childinfo.displayName.motherName", childInfo.getMotherName());
        crudView.getAttributeValue().put("crud.childinfo.displayName.description", childInfo.getDescription());
        crudView.getAttributeValue().put("crud.childinfo.displayName.birthDay", String.valueOf(childInfo.getBirthDay()));
        crudView.getAttributeValue().put("crud.childinfo.displayName.birthMonth", String.valueOf(childInfo.getBirthMonth()));
        crudView.getAttributeValue().put("crud.childinfo.displayName.birthYear", String.valueOf(childInfo.getBirthYear()));
        return crudView;
    }
}
