package fr.geeklegend.uhchost.server;

import lombok.Getter;

public enum ServerAccess
{
    OPEN("§aOuvert"),
    CLOSE("§cFermer"),
    WHITELIST("§bWhitelist");

    @Getter
    private String name;

    ServerAccess(String name)
    {
        this.name = name;
    }
}
