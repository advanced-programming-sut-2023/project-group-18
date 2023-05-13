package com.example.controller.Methods;

import java.util.Scanner;

public class StorageMethods implements SelectBuildingMenuMethods{
    private static StorageMethods storageMethods;
    private StorageMethods() {

    }
    public static StorageMethods getStorageMethods() {
        return storageMethods == null ? storageMethods = new StorageMethods() : storageMethods;
    }
    @Override
    public void run(Scanner scanner) {
        StorageMethods.getStorageMethods().run(scanner);
    }
}
