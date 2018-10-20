package com.example.adrian.recipeandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";

    // TODO: Rename and change types of parameters
    private int mPosition;

    private OnFragmentInteractionListener mListener;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 1.
     * @return A new instance of fragment RecipeDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeDetailFragment newInstance(int position) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        TextView textViewFragment= (TextView) view.findViewById(R.id.textViewFragment);

        switch(mPosition){
            case 0:
                textViewFragment.setText("Pela y pica la cebolla en dados medianos. Limpia el pimiento verde, retírale el tallo y las pepitas y córtalo en dados. Si las patatas estuvieran sucias, pásalas por agua. Pélalas, córtalas por la mitad a lo largo y después corta cada trozo en medias lunas finas de 1/2 centímetros. Introduce todo en la sartén, sazona a tu gusto y fríe a fuego suave durante 25-30 minutos. Retira la fritada y escúrrela. Pasa el aceite a un recipiente y resérvalo. Limpia la sartén con papel absorbente de cocina. Casca los huevos, colócalos en un recipiente grande y bátelos. Sálalos a tu gusto, agrega la fritada de patatas, cebolla y pimiento y mezcla bien. Coloca la sartén nuevamente en el fuego, agrega un chorrito del aceite reservado y agrega la mezcla. Remueve un poco con una cuchara de madera y espera (20 segundos) a que empiece a cuajarse. Separa los bordes, cubre la sartén con un plato de maRecetasr diámetro que la sartén y dale la vuelta. Échala de nuevo para que cuaje por el otro lado.");
                break;
            case 1:
                textViewFragment.setText("Compramos una pierna de cordero que pese unos dos kilos, con lo que podremos servir la receta para unos 3-4 comensales, acompañando la misma con alguna guarnición que nos guste, como puede ser una ensalada o unas papas fritas o asadas, algo que puedes hacer aparte, ya que en esta receta nos vamos a centrar en preparar la pata de cordero para que nos quede lo más sabrosa y jugosa posible, pero sin hacer ninguna guarnición con ella, eso lo dejamos a vuestra elección.Puedes pedir al carnicero que te prepare la pata para hornear haciendole unos cortes.Si prefieres puedes hacerlo tú mismo en casa, haciendo un par de cortes dividiendo la pierna en tres partes, pero sin llegar a separar los trozos, si no haciendo unos cortes profundos para que se hornee bien por todos lados. Una vez vayamos a comenzar a preparar la pierna de cordero, la vamos a lavar bien bajo el grifo, la escurrimos y secamos bien y después la salpimentamos bien por todos lados y la colocamos sobre una bandeja metálica del horno. Aprovechamos para poner el horno a precalentar a 220ºC, con el calor activado arriba y abajo, así lo tendremos bien caliente en cuanto acabemos la receta. Y en un recipiente en el que podamos batir vamos a echar los ajos pelados, el vino blanco, el aceite de oliva virgen y tomillo y romero al gusto, y batimos todo hasta que quede una mezcla homogénea. Colocamos la pata de cordero salpimentada sobre la bandeja elegida, y la pintamos con la mezcla que hemos preparado, de forma que la carne quede bien impregnada, y reservando lo que nos sobre para después. Agregamos un vaso de agua en la bandeja, para que el cordero no se reseque demasiado durante el horneado. Una vez el horno haya alcanzado la temperatura elegida la bajamos a 170ºC y metemos la bandeja en la parte central, con la pata de cordero con la parte interior hacia arriba, y vamos a hornearla sobre 60-70 minutos. Cuando le demos la vuelta vertemos el resto de la salsa que batimos antes, y dejamos hornear otros 40-45 minutos más o menos. Nos debe quedar la pierna de cordero bien asada por todos lados, de forma que la carne se despegue del hueso fácilmente, pero sin llegar a quemarse. Seguramente tras el tiempo indicado no esté aun en su punto, por lo que iremos volteando la misma hasta que por los dos lados esté bien horneada. Si quieres puedes darle un último golpe de calor con el grill, para que quede bien doradita. Cuando esté lista para servir, apagamos el horno y dejamos la pata unos minutos en la bandeja dentro del mismo, con la puerta abierta, y después la servimos en caliente para disfrutar de todo su sabor, que esperamos sea de vuestro agrado.");
                break;
            case 2:
                textViewFragment.setText("Limpia y pela las gambas. Usa las conchas y cabeza para hacer caldo. Resérvalo. Limpia los calamares y pícalos en ruedas. Limpia también las conchas de mar con abundante agua para que suelten cualquier residuo de arena. En una paellera, ó sartén muy grande y profunda, sofríe en aceite de oliva, la cebolla, el ajo, pimiento y tomate picados en cuadritos pequeños. Agrégale un poco de pimienta y sal. Pon los calamares y luego las conchas de mar. Deja que se cocinen unos minutos. Verás que comienza a hacerse un caldo, esto está bien. Agrega las 2 tazas de arroz y revuelve para que se mezcle todo. Seguidamente ponle 4 tazas del caldo de gambas que hiciste anteriormente. Si no te alcanza, completa con agua. Revuelve bien. Agrega una cucharadita de colorante amarillo ó las hebras de azafrán y deja hervir por unos 3 minutos. Aun con líquido en la paellera, agrega los guisantes frescos, las gambas y el perejil previamente picado muy pequeñito. Chequea la sal y la pimienta y agrega de ser necesario. Deja cocinar hasta que esté casi seco el líquido. En este momento puedes ponerle unas tiras de pimiento para decorar y algunos langostinos con su concha. Baja el fuego y tapa. Deja cocinar por 15 minutos y prueba el grano. Si está listo retira del fuego y sirve tu paella de marisco con un chorro de aceite de oliva por encima para darle aun más sabor. Y ya esta lista nuestra paella");
                break;
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
