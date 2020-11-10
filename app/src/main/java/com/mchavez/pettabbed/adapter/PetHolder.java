package com.mchavez.pettabbed.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mchavez.pettabbed.R;
import com.mchavez.pettabbed.datos.PetConstr;

public class PetHolder extends RecyclerView.ViewHolder {

    private static ImageView imgPet;

    public static ImageButton btnHueso;
    private static TextView nombrePet;
    private static TextView rating;
    private static PetHolder holder;
    static Context context;
    //private static Activity activity;

    public PetHolder(@NonNull View itemView) {
        super(itemView);

        imgPet = itemView.findViewById(R.id.imgPet);
        btnHueso = itemView.findViewById(R.id.btnHueso);
        nombrePet = itemView.findViewById(R.id.nombrePet);
        rating = itemView.findViewById(R.id.rating);
        //ImageButton btnHueso2 = itemView.findViewById(R.id.btnHueso2);
    }

    public static void init(final Mascota mascota, final Context context) {

        imgPet.setImageResource((mascota.getFoto()));
        nombrePet.setText(mascota.getNombre());
        rating.setText(String.valueOf(mascota.getRating()));
        btnHueso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                //Toast.makeText(objActivity, "Diste Like a " + objPet.getNombre(), Toast.LENGTH_SHORT).show();
                //Con esto se inserta un like en la base de datos
                PetConstr constructorPets = new PetConstr(v.getContext());
                constructorPets.darLikePet(mascota);
                rating.setText(String.valueOf(constructorPets.obtenerLikesPet(mascota)));

                /*
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.inflate(R.menu.popup_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.item1:
                                Toast.makeText(v.getContext(),mascota.getNombre()+": Te gusto un: 5" , Toast.LENGTH_SHORT).show();
                                holder.rating.setText("5");
                                return true;
                            case R.id.item2:
                                Toast.makeText(v.getContext(),mascota.getNombre()+": Te gusto un: 4" , Toast.LENGTH_SHORT).show();
                                holder.rating.setText("4");
                                return true;
                            case R.id.item3:
                                Toast.makeText(v.getContext(),mascota.getNombre()+": Te gusto un: 3" , Toast.LENGTH_SHORT).show();
                                holder.rating.setText("3");
                                return true;
                            case R.id.item4:
                                Toast.makeText(v.getContext(),mascota.getNombre()+": Te gusto un: 2" , Toast.LENGTH_SHORT).show();
                                holder.rating.setText("2");
                                return true;
                            case R.id.item5:
                                Toast.makeText(v.getContext(),mascota.getNombre()+": Te gusto un: 1" , Toast.LENGTH_SHORT).show();
                                holder.rating.setText("1");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
                 */

            }
        });
    }
}