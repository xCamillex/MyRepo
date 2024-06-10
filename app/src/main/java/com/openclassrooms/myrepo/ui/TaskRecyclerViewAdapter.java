package com.openclassrooms.myrepo.ui;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.openclassrooms.myrepo.R;
import com.openclassrooms.myrepo.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Un adaptateur pour afficher la liste de tâches dans un RecyclerView.
 */
public class TaskRecyclerViewAdapter extends ListAdapter<Task, TaskRecyclerViewAdapter.ViewHolder> {

    /**
     * Constructeur de l'adaptateur.
     */
    public TaskRecyclerViewAdapter() {
        super(new ItemCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    /**
     * ViewHolder pour afficher les éléments de la liste de tâches.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView factTextView;

        private final TextView dueTimeTextView;

        private final LinearProgressIndicator progressIndicator;

        /**
         * Constructeur du ViewHolder.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            factTextView = itemView.findViewById(R.id.task_description);
            dueTimeTextView = itemView.findViewById(R.id.task_duetime);
            progressIndicator = itemView.findViewById(R.id.progress_horizontal);
        }

        /**
         * Lie les données de la tâche au ViewHolder.
         *
         * @param task La tâche à afficher.
         */
        public void bind(Task task) {
            factTextView.setText(task.getDescription());
            //instancie la classe SimpleDateFormat pour afficher les dates sous formes "jours/mois/année"
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            //je formate la date d'échéance en une chaine de caractère
            String formattedDueTime = dateFormat.format(task.getDueTime());
            //met a jour dueTimeTextView et affiche une chaîne descriptive suivie de la date d'échéance.
            dueTimeTextView.setText("Date limite :" + formattedDueTime);
            //calcul la progression d'une tâche en fonction de sa date d'échéance et met à jour l'indicateur
            int progress = calculateProgress(task.getDueTime());
            progressIndicator.setProgress(progress);
        }

        /**
         * Calcule la valeur de progression en pourcentage pour la barre de progression.
         * @param dueTime La date d'échéance de la tâche.
         * @return La valeur de progression en pourcentage.
         */
        private int calculateProgress(Date dueTime){
            Calendar calendarToday = Calendar.getInstance();//initialise la date du jour sans les heures, minutes,...
            calendarToday.set(Calendar.HOUR_OF_DAY,0);
            calendarToday.set(Calendar.MINUTE,0);
            calendarToday.set(Calendar.SECOND,0);
            calendarToday.set(Calendar.MILLISECOND,0);

            Calendar calendarDueTime = Calendar.getInstance();////initialise la date d'échéance sans les heures, minutes,...
            calendarDueTime.setTime(dueTime);
            calendarDueTime.set(Calendar.HOUR_OF_DAY,0);
            calendarDueTime.set(Calendar.MINUTE,0);
            calendarDueTime.set(Calendar.SECOND,0);
            calendarDueTime.set(Calendar.MILLISECOND,0);

            int daysDifference = (int) //calcule entre les 2 dates en miliseconde pour retourner le résultat en jours
                    ((calendarDueTime.getTimeInMillis()- calendarToday.getTimeInMillis()) / (24*60*60*1000));
            return 100 - (daysDifference * 10); //calcule en pourcentage
        }
    }

    /**
     * Callback pour la comparaison des éléments de la liste.
     */
    private static class ItemCallback extends DiffUtil.ItemCallback<Task> {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getDescription().equals(newItem.getDescription());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.equals(newItem);
        }
    }
}
