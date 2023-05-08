package com.pf_nxsp_myfood.backend.domain.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseUtils {
    List<String> inspect() {
        Field[] fields = this.getClass().getDeclaredFields();
        List<String> fieldValue = new ArrayList<>();

        for (Field f : fields) {
            fieldValue.add(f.getName());
        }

        return fieldValue;
    }

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        Gson gson = new Gson();

        for(String name : inspect()) {
            String method = "get" + Utils.capitalize(name);
            try {
                json.addProperty(
                        name,
                        String.valueOf(
                                this.getClass()
                                        .getMethod(method)
                                        .invoke(this)
                        ));
            } catch (RuntimeErrorException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        return gson.toJson(json);
    }
}
