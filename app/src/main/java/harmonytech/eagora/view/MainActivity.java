package harmonytech.eagora.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import harmonytech.eagora.R;
import harmonytech.eagora.controller.domain.Segment;
import harmonytech.eagora.controller.fragment.SegmentFragment;
import harmonytech.eagora.controller.util.Utility;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    ArrayList<Segment> recipesList = new ArrayList<>();
    SegmentFragment frag;

    Button btnRegisterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegisterService = (Button) findViewById(R.id.btnRegisterService);
        btnRegisterService.setOnClickListener(this);

        setupUI();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupUI() {

        final VideoView videoview = (VideoView) findViewById(R.id.video);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.eagora_clip);
        videoview.setVideoURI(uri);
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoview.start();
            }
        });

        Segment animal = new Segment();
        animal.setName(Utility.SEGMENTO_ANIMAIS);
        Uri uriAnimal = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_animais);
        animal.setBanner(uriAnimal);
        recipesList.add(animal);

        Segment arteCultura = new Segment();
        arteCultura.setName(Utility.SEGMENTO_ARTE_CULTURA);
        Uri uriArteCultura = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_arte_cultura);
        arteCultura.setBanner(uriArteCultura);
        recipesList.add(arteCultura);

        Segment assistenciaTecnica = new Segment();
        assistenciaTecnica.setNameFirebase(Utility.SEGMENTO_ASSISTENCIA_TECNICA_FIREBASE);
        assistenciaTecnica.setName(Utility.SEGMENTO_ASSISTENCIA_TECNICA);
        Uri uriAssistenciaTecnica = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_assistencia);
        assistenciaTecnica.setBanner(uriAssistenciaTecnica);
        recipesList.add(assistenciaTecnica);

        Segment aulas = new Segment();
        aulas.setName(Utility.SEGMENTO_AULAS);
        Uri uriAulas = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_aulas);
        aulas.setBanner(uriAulas);
        recipesList.add(aulas);

        Segment autos = new Segment();
        autos.setName(Utility.SEGMENTO_AUTOS);
        Uri uriAutos = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_servicos_carro);
        autos.setBanner(uriAutos);
        recipesList.add(autos);

        Segment belezaEstetica = new Segment();
        belezaEstetica.setName(Utility.SEGMENTO_BELEZA_ESTETICA);
        Uri uriBelezaEstetica = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_beleza_estetica);
        belezaEstetica.setBanner(uriBelezaEstetica);
        recipesList.add(belezaEstetica);

        Segment construcao = new Segment();
        construcao.setName(Utility.SEGMENTO_CONSTRUCAO);
        Uri uriConstrucao = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_reforma);
        construcao.setBanner(uriConstrucao);
        recipesList.add(construcao);

        Segment consultoria = new Segment();
        consultoria.setName(Utility.SEGMENTO_CONSULTORIA);
        Uri uriConsultoria = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_consultoria);
        consultoria.setBanner(uriConsultoria);
        recipesList.add(consultoria);

        Segment design = new Segment();
        design.setName(Utility.SEGMENTO_DESIGN);
        Uri uriDesign = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_design);
        design.setBanner(uriDesign);
        recipesList.add(design);

        Segment eventos = new Segment();
        eventos.setName(Utility.SEGMENTO_EVENTOS);
        Uri uriEventos = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_eventos);
        eventos.setBanner(uriEventos);
        recipesList.add(eventos);

        Segment saude = new Segment();
        saude.setName(Utility.SEGMENTO_SAUDE);
        Uri uriSaude = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_saude);
        saude.setBanner(uriSaude);
        recipesList.add(saude);

        Segment servicosDomesticos = new Segment();
        servicosDomesticos.setName(Utility.SEGMENTO_SERVICOS_DOMESTICOS);
        Uri uriServicoDomestico = Uri.parse(Utility.URI_PACKAGE + R.drawable.eagora_image_servicos_domesticos);
        servicosDomesticos.setBanner(uriServicoDomestico);
        recipesList.add(servicosDomesticos);

        frag = (SegmentFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null) {
            frag = new SegmentFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.segments_fragment_container, frag, "mainFrag");
            ft.commit();
        }
    }

    public List<Segment> getRecipesList() {
        return recipesList;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btnRegisterService:
                startActivity(new Intent(this, RegisterServiceActivity.class));
                break;
        }
    }
}
