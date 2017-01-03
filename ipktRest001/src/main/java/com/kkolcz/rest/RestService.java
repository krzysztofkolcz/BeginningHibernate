package com.kkolcz.rest;
import java.io.ByteArrayOutputStream;

import com.kkolcz.types.UserData;

public interface RestService {
    public UserData getUserData(String login);
}

