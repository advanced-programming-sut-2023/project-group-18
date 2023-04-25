package com.example.model;

import java.io.File;

public interface NeedsId {

    int getNextId();

    void goToNextId();

    File getNextIdFile();

}
