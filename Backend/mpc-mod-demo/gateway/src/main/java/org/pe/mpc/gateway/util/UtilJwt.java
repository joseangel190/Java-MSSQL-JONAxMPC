/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.gateway.util;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import java.util.Map;

/**
 *
 * @author jtorresb
 */
public class UtilJwt {
    
    public Map<String,Object> extractDataFromToken(String token) {
        try {
            JWT jwt = JWTParser.parse(token);
            JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
            return claimsSet.getClaims();
        } catch (Exception e) {
            return null;
        }
    }
    
}
