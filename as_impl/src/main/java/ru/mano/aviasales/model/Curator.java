package ru.mano.aviasales.model;

import java.util.Arrays;
import java.util.List;

public class Curator {
    private String name;
    private List<CuratorSkills> skills;

    public enum CuratorSkills {
        EYES, INFORMATION, MEMORY, EARS;
    }

    public Curator() {
        this.skills = Arrays.asList(CuratorSkills.values());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CuratorSkills> getSkills() {
        return skills;
    }

    public void setSkills(List<CuratorSkills> skills) {
        this.skills = skills;
    }

    public boolean hasEars() {
        for (CuratorSkills skill : skills) {
            if (skill == CuratorSkills.EARS)
                return true;
        }

        return false;
    }
    public String helpStudent() {
        return "I help you";
    }
}
