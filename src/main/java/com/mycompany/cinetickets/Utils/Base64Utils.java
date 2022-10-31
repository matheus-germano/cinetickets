/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinetickets.Utils;

import java.util.Base64;

/**
 *
 * @author matheus
 */
public class Base64Utils {
    public String encode(String toEncode) {
        return Base64.getEncoder().encodeToString(toEncode.getBytes());
    }
    
    public String decode(String toDecode) {
        byte[] decodedBytes = Base64.getDecoder().decode(toDecode);
        return new String(decodedBytes);
    }
}
