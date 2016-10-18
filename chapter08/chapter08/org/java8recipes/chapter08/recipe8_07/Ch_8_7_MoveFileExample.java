/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.java8recipes.chapter08.recipe8_07;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 *
 * @author Juneau
 */
public class Ch_8_7_MoveFileExample {
    public static void main (String[] args) {
        Ch_8_7_MoveFileExample exampleCh87 = new Ch_8_7_MoveFileExample();
        exampleCh87.moveFile();
    }

    private void moveFile() {
        FileSystem fileSystem = FileSystems.getDefault();
        Path sourcePath = fileSystem.getPath("file.log");
        Path targetPath = fileSystem.getPath("file2.log");
        System.out.println("Move from "+sourcePath.toAbsolutePath().toString()+" to "+targetPath.toAbsolutePath().toString());
        try {
            Files.move(sourcePath, targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
