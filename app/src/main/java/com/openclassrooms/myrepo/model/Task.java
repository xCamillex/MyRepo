package com.openclassrooms.myrepo.model;

import java.util.Date;
import java.util.Objects;

/**
 * Classe représentant une tâche avec sa description et sa date d'échéance.
 */
public class Task {
    private String description;

    private Date dueTime;

    /**
     * Obtient la date d'échéance de la tâche.

     * @return La date d'échéance de la tâche.
     */
    public Date getDueTime() {

        return dueTime;
    }

    /**
     * Définit la date d'échéance de la tâche.
     *
     * @param dueTime La nouvelle date d'échéance de la tâche.
     */
    public void setDueTime(Date dueTime) {

        this.dueTime = dueTime;
    }

    /**
     * Constructeur pour créer une nouvelle tâche sa description et sa date d'échéance.
     *     * @param description La description de la tâche.
     *     * @param dueTime La date d'échéance de la tâche.
     */
    public Task(String description, Date dueTime) {

        this.description = description;

        this.dueTime = dueTime;

    }

    /**
     * Obtient la description de la tâche.
     *
     * @return La description de la tâche.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description de la tâche.
     *
     * @param description La nouvelle description de la tâche.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Vérifie si deux objets Task sont égaux en comparant leurs descriptions et leurs dates d'échéance.
     *     * @param o L'objet à comparer.
     *     * @return Vrai si les descriptions et les dates d'échéance sont égales, sinon faux.
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(description, task.description) && Objects.equals(dueTime,task.dueTime);
    }

    /**
     * Calcule le code de hachage en utilisant la description et la date d'échéance de la tâche.
     *     * @return Le code de hachage calculé.
     */
    @Override
    public int hashCode() {

        return Objects.hash(description, dueTime);
    }
}
