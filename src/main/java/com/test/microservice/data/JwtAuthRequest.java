package com.test.microservice.data;

import lombok.Data;

@Data
public class JwtAuthRequest
{
    private String username;
    private String password;

    public JwtAuthRequest ()
    {
        super();
    }

    public JwtAuthRequest (String username, String password)
    {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }
}
