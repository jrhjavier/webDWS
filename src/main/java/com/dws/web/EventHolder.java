package com.dws.web;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EventHolder {

    private Map<Long, Event> events = new ConcurrentHashMap<>();
    private AtomicLong lastIDEvent = new AtomicLong();

    private AtomicLong lastIDReview = new AtomicLong();

    public EventHolder(){
        long l = 10000;
        Event e1 = new Event("Teleferico", "Ocio", "El Teleférico (con accesos en la Estación del Paseo del Pintor Rosales y en la Estación de Casa de Campo) es una magnífica oportunidad para sobrevolar la parte más verde de la ciudad y contemplar desde el aire su parte más histórica. Además de ofrecer unas vistas panorámicas inmejorables, el Teleférico es un medio de transporte rápido y sostenible para llegar al corazon de la Casa de Campo", 6);
        Event e2 = new Event("Barcas Retiro", "Ocio", "Actualmente, el estanque ofrece varias actividades dentro del Centro Deportivo Municipal Estanque del Retiro, como barcas de remo (se dispone de 100 barcas, dos de ellas accesibles para personas en silla de ruedas), barco solar (accesible para silla de ruedas) y aula solar y la Escuela Municipal de Piragüismo, dirigida a niños y jóvenes entre 7 y 17 años.", 6);
        Event e3 = new Event("Parque de Atracciones", "Ocio", "El Parque de Atracciones de Madrid es un parque de atracciones situado en Madrid, en la Comunidad de Madrid, en España, concretamente en la zona de Batán de la Casa de Campo.", 25);
        Event e4 = new Event("Teatro Flamenco", "Ocio", "Disfruta del mejor espectáculo de Flamenco en el centro de Madrid. Reserva ya tus entradas.", 20);
        Event e5 = new Event("Zoo Madrid", "Ocio", "El Zoo Aquarium de Madrid es un zoológico de Madrid (España) que se encuentra en la Casa de Campo, gran parque forestal de la ciudad de Madrid. El parque pertenece a Parques Reunidos.", 20);
        Event e6 = new Event("Autobus Turistico", "Ocio", "Visita Madrid a tu ritmo y sin prisas a bordo de un autobús turístico de dos pisos. Sube y baja durante todo el día con el mismo billete, cambia de ruta y conoce la historia de Madrid mediante un sistema de audio por auriculares, ¡disponible en catorce idiomas distintos!", 22);
        Event e7 = new Event("Museo del Prado", "Ocio", "El Museo Nacional del Prado, en Madrid, España, es uno de los más importantes del mundo, así como uno de los más visitados (el decimoctavo en 2013 entre los museos de arte), y está considerada la institución cultural más importante de España, según el Observatorio de la Cultura de 2020, estudio realizado entre varios centenares de profesionales del sector.", 15);
        Event e8 = new Event("Filandon", "Restaurante", "Reunión alrededor del fuego donde se contaban cuentos,se recitaban romances,se hablaba de los chascarrillos de los pueblos,incluso se cantaba y se bailaba", 60);
        Event e9 = new Event("Casa Julian de Tolosa", "Restaurante", "A la clásica chuleta y pimientos del piquillo elaborados ambos con las mismas técnicas que en Tolosa, Mikel Gorrotxategi suma a la oferta culinaria unas alubias de Tolosa que son icónicas en Madrid. Genuino asador vasco en el corazón de la Latina.", 65);
        Event e10 = new Event("Viva Madrid", "Restaurante", "Fundado en 1856 como taberna, IQOptions conserva su antigua fachada de cerámica con vivos colores y la Plaza Cibeles como motivo principal pintados a mano por la prestigiosa Casa Mensaque a finales de 1920, el techo artesonado, la barra de estaño y su entorno, convierten a Viva Madrid en un lugar singular que nos transporta en el tiempo.", 20);
        Event e11 = new Event("Bodega de la Ardosa", "Restaurante", "En 1892, D. Rafael Fernández Bagena creó y fundó la famosa Cadena de Bodegas LA ARDOSA de Madrid. Propietario de la comarca vitivinícola llamada «La Ardosa» y ubicada en tierras de Toledo, D. Rafael decidió comercializar sus vinos en Madrid creando esta popular cadena que llegaría a pasar de la treintena", 20);
        Event e12 = new Event("La Ancha", "Restaurante", "La Ancha, un restaurante familiar con más de 100 años de historia donde la calidad de la comida se sostiene con recetas sin artificios, sencillas y tradicionales de puro producto. La carta cambia cada día como garantía de la frescura y calidad de producto de mercado. Raciones generosas y máxima atención al que nos visita, con un personal sincero, profesional, fiel, humilde y comprometido para que disfrute de la visita y se sienta como en casa.", 30);
        Event e13 = new Event("Malacatin", "Restaurante", "Malacatín es uno de los doce Restaurantes Centenarios de Madrid, un viaje al pasado, una forma de entender nuestra cocina, cultura y tradiciones, en un pequeño rincón del Madrid más castizo de finales de siglo XIX. Incluso su decoración se mantiene intacta desde hace décadas y alberga pequeñas historias en cada uno de sus rincones que nos trasladan a ese Madrid del pasado, ese que casi ya no existe.", 22);
        Event e14 = new Event("Lhardy", "Restaurante", "Alta cocina europea en un emblemático local señorial con dulces, productos gastronómicos y tienda de comida.", 40);
        Event e15 = new Event("Gran Via", "Turismo", "La Gran Vía es una de las principales calles de la ciudad española de Madrid. Comienza en la calle de Alcalá y termina en la plaza de España. Es un importante hito en la ciudad desde su construcción a principios de siglo xx visto desde el punto de vista comercial, turístico y de ocio.", 0);
        Event e16 = new Event("Plaza Mayor", "Turismo", "La plaza Mayor está situada en el centro de Madrid, en el barrio de Sol, junto a la calle Mayor y a 300 metros de la Puerta del Sol.", 0);
        Event e17 = new Event("Puerta del Sol", "Turismo", "La Puerta del Sol es una plaza de la ciudad española de Madrid. En ella se encuentra desde 1950 el denominado Kilómetro Cero de las carreteras radiales del país. El edificio más antiguo de la Puerta del Sol es la Casa de Correos y en ella destaca el reloj de torre que fue construido y donado en el siglo xix por José Rodríguez de Losada, y cuyas campanadas de las 12 de la noche del 31 de diciembre marcan la tradicional toma de las doce uvas a la gran mayoría de los españoles.", 0);
        Event e18 = new Event("Santiago Bernabeu", "Turismo", "Santiago Bernabéu&description=El Estadio Santiago Bernabéu es un recinto deportivo propiedad del Real Madrid Club de Fútbol, situado en pleno paseo de la Castellana, en el distrito de Chamartín de Madrid, España. Se inauguró el 14 de diciembre de 1947 y su aforo actualmente es de 81 044 espectadores.", 15);
        Event e19 = new Event("Jardin Botanico", "Turismo", "El Real Jardín Botánico de Madrid es un centro de investigación del Consejo Superior de Investigaciones Científicas. Fundado por Real Orden de 17 de octubre de 1755 por el rey Fernando VI en el Soto de Migas Calientes, cerca del río Manzanares, Carlos III ordenó el traslado a su situación actual en 1781, al Paseo del Prado, junto al Museo de Ciencias Naturales que se estaba construyendo (actualmente Museo del Prado), en Madrid, España", 6);
        Event e20 = new Event("Palacio Real", "Turismo", "El Palacio Real de Madrid, popularmente conocido también como Palacio de Oriente, es la residencia oficial del Jefe del Estado, el rey de España; no obstante, los actuales reyes no habitan en él, sino en el Palacio de la Zarzuela, por lo que es utilizado para ceremonias de Estado y actos solemnes.", 12);
        Event e21 = new Event("Catedral de la Almudena", "Turismo", "La Santa Iglesia Catedral Metropolitana de Santa María la Real de la Almudena, conocida simplemente como Catedral de la Almudena, es una catedral de culto católico, dedicada a la Virgen María bajo la advocación de la Almudena, y sede episcopal de Madrid.", 6);


        events.put(l++, e1);
        events.put(l++, e2);
        events.put(l++, e3);
        events.put(l++, e4);
        events.put(l++, e5);
        events.put(l++, e6);
        events.put(l++, e7);
        events.put(l++, e8);
        events.put(l++, e9);
        events.put(l++, e10);
        events.put(l++, e11);
        events.put(l++, e12);
        events.put(l++, e13);
        events.put(l++, e14);
        events.put(l++, e15);
        events.put(l++, e16);
        events.put(l++, e17);
        events.put(l++, e18);
        events.put(l++, e19);
        events.put(l++, e20);
        events.put(l, e21);

    }

    public void addEvent (Event event){
        long id = this.lastIDEvent.incrementAndGet();
        event.setIdEvent(id);
        this.events.put(id, event);
    }

    public Collection<Event> getEvents(){
        return this.events.values();
    }

    public Event getEvent(long id){
        return this.events.get(id);
    } //Por id

    public Long getEvent(Event e){
        return e.getId();
    } //Por evento

    public Event deleteEvent(long id){
        Event event = this.events.get(id);
        this.events.remove(id);
        return event;  //el return para que?
    }

    public AtomicLong getLastIDEvent() {
        return this.lastIDEvent;
    }

    public Collection<Event> getEventsFilteredByCategory(String category){  //eventos filtrados por categoria
        Collection<Event> l=null;
        for (Event e : this.events.values()){
            if (e.sameCategory(category)){
                l.add(e);
            }
        }
        return l;
    }

    public Event getEventByName(String name){
        for (Event e: this.events.values()){
            if (e.getName().equalsIgnoreCase(name)){
                return e;
            }
        }
        return null;
    }


/*

    public void addReview(Event e, Review r){
        long id = r.getLastID().incrementAndGet();
        r.setIdReview(id);
        e.setReview(r);
    }

    public Collection<Review> getAllReviews(Event e) {
        return e.getReviews();
    }

    public void deleteReview(Event e, Review r1) {
        e.eventContainsReview(r1);
        if (e != null) {
            e.deleteReviewOfAnEvent(r1);
        }
    }

    public void cleanReviews(Event e) {
        e.cleanReviews();
    }


    public boolean containsReview(Event e, Review r1) {
        e.eventContainsReview(r1);
        return r1!=null;
    }

    private Review inReviews(Event e, Review r1) {

        e.eventContainsReview(r1);

        if (e!=null){
            return r1;
        }
        else{
            return null;
        }
    }

    public Review getReview(Event e, long id){
        Review r=e.getReview(id);
        return e.getReview(r);
    }


    public Collection<Review> getReviewsOfAnEvent(Event e){
        Collection<Review> l=null;

        for (Review r : e.getReviews()){
            if (r.reviewOfAnEvent(e)){
                l.add(r);
            }
        }
        return l;
    }

    public Collection<Review> getReviewsOfAnEventCategory(String category){
        Collection<Event> eventsFilteredByCategory=this.getEventsFilteredByCategory(category);


        for (Event e : ){
            if (r.reviewOfAnEvent(e)){
                l.add(r);
            }
        }
        return l;
    }


    public Collection<Review> getReviewsOfAClient(Event e, Customer c){

        Collection<Review> l= null;

        for (Review r : e.getReviews()){
            if (r.reviewOfAClient(c)){
                l.add(r);
            }
        }
        return l;
    }

    public int eventStarsAverage(Event e){

        Collection<Review> reviewsOfAnEvent=this.getReviewsOfAnEvent(e);

        int suma=0;

        for (Review r : reviewsOfAnEvent){
            suma+=r.getStars();
        }

        return suma/reviewsOfAnEvent.size();
    }


 */

}