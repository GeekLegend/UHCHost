package fr.geeklegend.server;

import fr.geeklegend.util.Constant;
import lombok.Getter;

public enum HostRank
{
    MAIN(Constant.HOST_RANK_MAIN_NAME),
    SECONDARY(Constant.HOST_RANK_SECONDARY_NAME);

    @Getter
    private String name;

    HostRank(String name)
    {
        this.name = name;
    }
}
