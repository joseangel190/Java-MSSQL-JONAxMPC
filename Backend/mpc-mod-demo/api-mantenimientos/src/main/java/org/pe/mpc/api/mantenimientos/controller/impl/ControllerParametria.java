/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.api.mantenimientos.controller.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.pe.mpc.api.mantenimientos.controller.inter.InterControllerParametria;
import org.pe.mpc.demo.dto.beans.DtoParametria;
import org.pe.mpc.demo.entity.Parametria;
import org.pe.mpc.process.mantenimiento.inter.InterProcessParametria;
import java.util.Collection;
import java.util.Set;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author apoyo1953
 */
@RestController
@RequestMapping("api/mantenimientos/parametrias")
public class ControllerParametria implements InterControllerParametria {

    @Autowired
    private InterProcessParametria process;

    @ResponseBody
    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<String> save(@RequestBody Parametria entidad, HttpServletRequest request) throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validador = factory.getValidator();
        Set<ConstraintViolation<Parametria>> constraintsViolations = validador.validate(entidad);
        if (constraintsViolations.isEmpty()) {
            //entidad.setUsuario(request.getHeader("X-User-Email"));
            process.insert(entidad);
            return ResponseEntity.ok("Guardado correctamente");
        } else {
            return new ResponseEntity<>(constraintsViolations.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PutMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<String> update(@RequestBody Parametria entidad, HttpServletRequest request) throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validador = factory.getValidator();
        Set<ConstraintViolation<Parametria>> constraintsViolations = validador.validate(entidad);
        if (constraintsViolations.isEmpty()) {
            entidad.setUsuario(request.getHeader("X-User-Email"));
            //process.update(entidad);
            return ResponseEntity.ok("Actualizado correctamente");
        } else {
            return new ResponseEntity<>(constraintsViolations.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @DeleteMapping(value = "/deleteProcedure", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<String> delete(@RequestBody DtoParametria entidad, HttpServletRequest request) throws Exception {
        process.deleteProcedureJson(entidad);
        return ResponseEntity.ok("Eliminado correctamente");
    }

    @ResponseBody
    @PostMapping(value = "/find/parents", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public Collection<Parametria> parents() throws Exception {
        return null;//return process.parents();
    }

    @ResponseBody
    @PostMapping(value = "/find/childrens", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public Collection<Parametria> childrens() throws Exception {
        return null;//return process.childrens();
    }

    @ResponseBody
    @PostMapping(value = "/find/childrensbyparent", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public Collection<Parametria> childrensByParents(@RequestBody JsonNode requestJson) throws Exception {
        return null;//return process.childrensByParents(requestJson.get("idParent").asLong());
    }

    @ResponseBody
    @PostMapping(value = "/find/entitywithchildren", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public Parametria getEntityByIdWithChildrens(@RequestBody JsonNode requestJson) throws Exception {
        return null;//return process.getEntityByIdWithChildrens(requestJson.get("id").asLong());
    }

    @ResponseBody
    @PostMapping(value = "/find/entitywithoutchildren", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public Parametria getEntityByIdWithoutChildrens(@RequestBody JsonNode requestJson) throws Exception {
        return null;//return process.getEntityByIdWithoutChildrens(requestJson.get("id").asLong());
    }

    @ResponseBody
    @PostMapping(value = "/find/entity", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<?> getEntityById(@RequestBody JsonNode requestJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.registerModule(new Hibernate6Module());
        Parametria param = null;//process.getEntityById(requestJson.get("id").asLong());
        if (param != null) {
            String json = objectMapper.writeValueAsString(param);
            return ResponseEntity.ok(json);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parametria no encontrado o esta eliminado");
        }
    }

    @ResponseBody
    @PostMapping(value = "/listar/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<?> listar() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.registerModule(new Hibernate6Module());
        String json = objectMapper.writeValueAsString(process.listProcedure());
        return ResponseEntity.ok(json);
    }

    @ResponseBody
    @PostMapping(value = "/listar/paginacion", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<?> listar(@RequestParam("limit") Long limit, @RequestParam("offSet") Long offSet) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.registerModule(new Hibernate6Module());
        String json = objectMapper.writeValueAsString(process.listProcedurePaginacion(limit, offSet));
        return ResponseEntity.ok(json);
    }

    @ResponseBody
    @PostMapping(value = "/saveProcedure", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<String> saveProcedure(@RequestBody DtoParametria entidad, HttpServletRequest request) throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validador = factory.getValidator();
        Set<ConstraintViolation<DtoParametria>> constraintsViolations = validador.validate(entidad);
        if (constraintsViolations.isEmpty()) {
            //entidad.setUsuario(request.getHeader("X-User-Email"));
            process.insertProcedureJson(entidad);
            return ResponseEntity.ok("Guardado correctamente");
        } else {
            return new ResponseEntity<>(constraintsViolations.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseBody
    @PutMapping(value = "/updateProcedure", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<String> updateProcedure(@RequestBody DtoParametria entidad, HttpServletRequest request) throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validador = factory.getValidator();
        Set<ConstraintViolation<DtoParametria>> constraintsViolations = validador.validate(entidad);
        if (constraintsViolations.isEmpty()) {
            //entidad.setUsuario(request.getHeader("X-User-Email"));
            process.updateProcedureJson(entidad);
            return ResponseEntity.ok("Actualizado correctamente");
        } else {
            return new ResponseEntity<>(constraintsViolations.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
