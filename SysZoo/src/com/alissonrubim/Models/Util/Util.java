/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.Util;

import com.alissonrubim.Models.IModel;
import java.util.ArrayList;

/**
 *
 * @author alisson.rubim
 */
public class Util {
    public static int findObjectIndexInArray(ArrayList list, int id){
        int result = -1;
        for(int i = 0; i<list.size(); i++){
            if(((IModel)list.get(i)).getId() == id){
                result = i;
                break;
            }
        }
        return result;
    }
}
