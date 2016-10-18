
package org.java8recipes.chapter02.recipe2_12;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Recipe 2-12:  Nashorn
 * @author Juneau
 */
public class Recipe02_12 {

    public static void loadInlineJs() {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine nashorn = sem.getEngineByName("nashorn");
        try {
            nashorn.eval("print('This is being printed with JavaScript');");
        } catch (ScriptException ex) {
            Logger.getLogger(Recipe02_12.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] main) {
        loadInlineJs();
    }

}
