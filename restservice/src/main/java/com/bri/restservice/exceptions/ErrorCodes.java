/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bri.restservice.exceptions;

/**
 *
 * @author CODE.ID
 */
public final class ErrorCodes {
    
    private ErrorCodes(){
        
    }
    public static final int UNKNOWN = -1;
    public static final int ENTITY_NOT_FOUND = 1;
    public static final int ENTITY_NOT_VALID = 2;
    public static final int PATH_NOT_FOUND = 3;
    
}
