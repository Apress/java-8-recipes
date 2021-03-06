
package org.java8recipes.chapter18.recipe18_03;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.java8recipes.chapter18.recipe18_01.NashornInvoker;

/**
 * Recipe 18-3:  Embedding Expressions in Strings
 * @author Juneau
 */
public class Recipe18_3 {
    
    public static void main(String[] args){
        loadEmbedded();
    }
    
    /**
     * This will fail because I cannot run an executable JS file which
     * contains a Shebang from the ScriptEngineManager.
     */
    public static void loadEmbedded(){
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine nashorn = sem.getEngineByName("nashorn");
        try {
            nashorn.eval("load('PATH-TO/org/java8recipes/chapter18/recipe18_3/js/recipe18_3.js');");
        } catch (ScriptException ex) {
            Logger.getLogger(Recipe18_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
