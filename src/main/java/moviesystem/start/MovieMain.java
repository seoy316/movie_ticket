package moviesystem.start;


import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.querydsl.core.group.GroupBy.*;


public class MovieMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("moviesystem");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    // 초기 데이터
    static void dataset() throws ParseException {
        User user1 = new User("장서윤", 24, "구미시", "양호동", "11");
        User user2 = new User("장현영", 24, "대구광역시", "달서구", "15");

        em.persist(user1);
        em.persist(user2);

        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        Movie movie3 = new Movie();
        Movie movie4 = new Movie();

        movie1.CreateMovie("학교 종강의 날", format.parse("2021-12-24"),Genre.COMEDY,120);
        movie2.CreateMovie("취업, 그놈이 도망간다", format.parse("2022-2-15"),Genre.HORROR,100);
        movie3.CreateMovie("5학년, 그놈이 쫓아온다", format.parse("2022-3-2"),Genre.SUSPENCE,110);
        movie4.CreateMovie("이터널스", format.parse("2021-11-3"),Genre.ACTION,155);

        em.persist(movie1);
        em.persist(movie2);
        em.persist(movie3);
        em.persist(movie4);

        Director director1 = new Director("봉준호", format.parse("1969-09-14"), "대구광역시");
        Director director2 = new Director("박찬욱",format.parse("1963-08-23"), "서울특별시");
        Director director3 = new Director("정이삭", format.parse("1978-10-19"), "미국 콜로라도 덴버");
        Director director4 = new Director("클로이 자오",format.parse("1982-03-31"), "중국 베이징시");

        //사전 데이터 : 배우
        Actor[] actors = {new Actor("장현영", format.parse("1998-12-24"), 163, "_hyeon_"),
                new Actor("차채원", format.parse("1998-06-06"), 163, "_chae_"),
                new Actor("황성인", format.parse("1999-02-26"), 163, "_sung_"),
                new Actor("홍지선", format.parse("1998-04-29"), 163, "_Zisun_"),
                new Actor("장서윤", format.parse("1998-03-16"),163, "_yoon_"),
                new Actor("홍소희", format.parse("1998-03-12"),163, "_soo_"),
                new Actor("안젤리나 졸리", format.parse("1975-06-04"),169, "angelinajolie"),
                new Actor("마동석", format.parse("1971-03-01"),178, "donlee"),
                new Actor("키트 해링턴", format.parse("1986-12-26"),173, "_kitte_"),
                new Actor("리차드 매든", format.parse("1986-06-18"), 177, "maddenrichard")
        };

        // 장현영
        MovieWorker movieWorker1 = new MovieWorker(RoleType.MAIN, movie1);
        em.persist(movieWorker1);

        MovieWorker movieWorker2 = new MovieWorker(RoleType.SUB, movie2);
        em.persist(movieWorker2);

        actors[0].addMovieWorker(movieWorker1);
        actors[0].addMovieWorker(movieWorker2);

        // 차채원
        MovieWorker movieWorker3 = new MovieWorker(RoleType.MAIN, movie1);
        em.persist(movieWorker3);

        MovieWorker movieWorker4 = new MovieWorker(RoleType.MAIN, movie3);
        em.persist(movieWorker4);

        actors[1].addMovieWorker(movieWorker3);
        actors[1].addMovieWorker(movieWorker4);

        // 황성인
        MovieWorker movieWorker5 = new MovieWorker(RoleType.SUB, movie1);
        em.persist(movieWorker5);

        MovieWorker movieWorker6 = new MovieWorker(RoleType.SUB, movie2);
        em.persist(movieWorker6);

        MovieWorker movieWorker20 = new MovieWorker(RoleType.MAIN, movie3);
        em.persist(movieWorker20);

        actors[2].addMovieWorker(movieWorker5);
        actors[2].addMovieWorker(movieWorker6);
        actors[2].addMovieWorker(movieWorker20);

        // 홍지선
        MovieWorker movieWorker7 = new MovieWorker(RoleType.SUB, movie1);
        em.persist(movieWorker7);

        MovieWorker movieWorker19 = new MovieWorker(RoleType.MAIN, movie2);
        em.persist(movieWorker19);

        actors[3].addMovieWorker(movieWorker7);
        actors[3].addMovieWorker(movieWorker19);

        // 장서윤
        MovieWorker movieWorker8 = new MovieWorker(RoleType.MAIN, movie2);
        em.persist(movieWorker8);

        MovieWorker movieWorker9 = new MovieWorker(RoleType.SUB, movie3);
        em.persist(movieWorker9);

        actors[4].addMovieWorker(movieWorker8);
        actors[4].addMovieWorker(movieWorker9);

        // 홍소희
        MovieWorker movieWorker10 = new MovieWorker(RoleType.SUB, movie3);
        em.persist(movieWorker10);

        actors[5].addMovieWorker(movieWorker10);

        // 이터널스
        MovieWorker movieWorker11 = new MovieWorker(RoleType.MAIN, movie4);
        MovieWorker movieWorker12 = new MovieWorker(RoleType.MAIN, movie4);
        MovieWorker movieWorker13 = new MovieWorker(RoleType.SUB, movie4);
        MovieWorker movieWorker14 = new MovieWorker(RoleType.SUB, movie4);

        em.persist(movieWorker11);
        em.persist(movieWorker12);
        em.persist(movieWorker13);
        em.persist(movieWorker14);

        actors[6].addMovieWorker(movieWorker11);
        actors[7].addMovieWorker(movieWorker12);
        actors[8].addMovieWorker(movieWorker13);
        actors[9].addMovieWorker(movieWorker14);


        // 봉준호
        MovieWorker movieWorker15 = new MovieWorker();
        movieWorker15.setMovie(movie1);
        em.persist(movieWorker15);

        director1.addMovieWorker(movieWorker15);

        // 박찬욱
        MovieWorker movieWorker16 = new MovieWorker();
        movieWorker16.setMovie(movie2);
        em.persist(movieWorker16);

        director2.addMovieWorker(movieWorker16);

        // 정이삭
        MovieWorker movieWorker17 = new MovieWorker();
        movieWorker17.setMovie(movie3);
        em.persist(movieWorker17);

        director3.addMovieWorker(movieWorker17);

        // 클로이자오
        MovieWorker movieWorker18 = new MovieWorker();
        movieWorker18.setMovie(movie4);
        em.persist(movieWorker18);

        director4.addMovieWorker(movieWorker18);


        for(Actor actor : actors){
            em.persist(actor);
        }

        em.persist(director1);
        em.persist(director2);
        em.persist(director3);
        em.persist(director4);

        //사전 데이터 : 상영관
        Theater theater1 = new Theater("상영관1", 1);
        Theater theater2 = new Theater("상영관2", 2);

        em.persist(theater1);
        em.persist(theater2);

        //사전 데이터 : 좌석
        Seat[] seats = {new Seat(1,1,theater1), new Seat(1,2, theater1), new Seat(1,3, theater1),
                new Seat(1,4, theater1), new Seat(1,5, theater1), new Seat(2,1, theater1),
                new Seat(2,2, theater1), new Seat(2,3, theater1), new Seat(2,4, theater1),
                new Seat(2,5, theater1), new Seat(1,1,theater2), new Seat(1,2, theater2),
                new Seat(1,3, theater2), new Seat(1,4, theater2), new Seat(1,5, theater2),
                new Seat(2,1, theater2), new Seat(2,2, theater2), new Seat(2,3, theater2),
                new Seat(2,4, theater2), new Seat(2,5, theater2)};

        for(Seat seat : seats){
            em.persist(seat);
        }

        //사전 데이터 : 상영 스케줄
        Screen[] screens = { new Screen(LocalTime.parse("09:00:00"), LocalTime.parse("11:00:00"), movie1, theater1),
                new Screen(LocalTime.parse("11:30:00"), LocalTime.parse("13:10:00"),movie2, theater1),
                new Screen(LocalTime.parse("17:00:00"), LocalTime.parse("19:35:00"),movie4, theater1),
                new Screen(LocalTime.parse("10:00:00"), LocalTime.parse("12:35:00"),movie4, theater2),
                new Screen(LocalTime.parse("14:00:00"), LocalTime.parse("15:50:00"),movie3, theater2),
                new Screen(LocalTime.parse("18:00:00"), LocalTime.parse("20:00:00"),movie1, theater2)
        };

        for ( Screen screen : screens) {
            em.persist(screen);
        }

        //사전 데이터 : 예매 티켓
        Ticket ticket1 = new Ticket(user1, screens[0]);
        Ticket ticket2 = new Ticket(user2, screens[0]);

        em.persist(ticket1);
        em.persist(ticket2);

        //사전 데이터 : 예매 좌석
        ScreenSeat[] screenSeats ={ new ScreenSeat(ticket1, seats[0]), new ScreenSeat(ticket1, seats[1]),
                new ScreenSeat(ticket2, seats[5]), new ScreenSeat(ticket2, seats[6])
        };

        for ( ScreenSeat screenSeat : screenSeats) {
            em.persist(screenSeat);
        }

    }

    //1.user 추가
    static void CreateUser(User user, String name, Integer age, String city, String street, String zipCode){
        user.setName(name);
        user.setAge(age);
        Address address = new Address(city, street, zipCode);
        user.setAddress(address);
        user.setCreateTime(LocalDateTime.now());
        em.persist(user);
    }

    //2.user 정보 수정
    static void EditUser(String findName, String editName){

        TypedQuery<User> query = em.createQuery("select u from User u where u.name=:findName", User.class);
        query.setParameter("findName",findName);
        User result = query.getSingleResult();
        System.out.println("result.getName() = " + result.getName());

        result.setName(editName);
        result.setEditTime(LocalDateTime.now());
    }

    // 3) 영화정보 조회1: 영화 레코드의 PK를 이용하여 한 영화에 대한 상세 정보 조회
    static void showMovieWithWorkerId(Integer movieId) {

        String jpqlMW = "SELECT mw FROM MovieWorker mw join fetch mw.movie join fetch mw.workers where mw.movie = "+movieId;

        List<MovieWorker> workers = em.createQuery(jpqlMW, MovieWorker.class).getResultList();

        // 감독/배우의 역할을 매칭해서 리스트에 저장하기 위함
        List <String> s = new ArrayList<String>();
        workers.stream().forEach(w->s.add(w.getWorkers().getName()+"("+w.getRoleType()+")"));

        workers.stream().findFirst().stream().forEach(w->System.out.println(
                "영화 이름 = " + w.getMovie().getName() + " " +
                "개봉일 : " + w.getMovie().getOpeningDate() + " " +
                "감독/배우 : " + s + " " +
                "장르 : " + w.getMovie().getGenre() + " " +
                "러닝타임 : " + w.getMovie().getRunningTime()
        ));
    }

    // 4) 영화정보 조회2: 다양한 검색 조건(감독, 배우, 개봉일)을 이용하여 조회 - QueryDSL 사용
    static void findMovieWithWorkerOpeningDateRunningTime(Worker director, Worker actor, Date openingDate) {

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMovie qMovie = QMovie.movie;
        QMovieWorker qMovieWorker = QMovieWorker.movieWorker;
        MovieSearchDTO param = new MovieSearchDTO(director, actor, openingDate);

        // BooleanExpression을 이용하여 동적쿼리 적용
        BooleanExpression directorContain = movieDirectorContain(param);
        BooleanExpression actorContain = movieActorContain(param);
        BooleanExpression openingDateEq = movieOpeningDate(param);

        List<Movie> movies = query.select(qMovie)
                .distinct()
                .from(qMovie, qMovieWorker)
                .join(qMovie.movieWorkers, qMovieWorker).fetchJoin()
                .where(directorContain, actorContain, openingDateEq)
                .fetch();
        if ((param.getOpeningDate() == null)
                & (param.getDirector() == null )
                & (param.getActor() == null)){  // 매개변수가 모두 null일 경우 아무것도 출력되지 않도록 함
            return;
        }
        movies.stream().forEach(m-> System.out.println(
                "\n제목 : " + m.getName() +
                "\n장르 : " + m.getGenre() +
                "\n러닝타임 : " + m.getRunningTime() +
                "\n개봉일 : " + m.getOpeningDate() +
                "\n배우/감독 : " + m.getMovieWorkers()));
    }

    private static BooleanExpression movieOpeningDate(MovieSearchDTO param) {
        if (param.getOpeningDate()==null || param.getOpeningDate().equals("")) {
            System.out.println("openingDate is null");
            return null;
        }
        return QMovie.movie.openingDate.between(param.getOpeningDate(), param.getOpeningDate());
    }

    private static BooleanExpression movieDirectorContain(MovieSearchDTO param) {
        if (param.getDirector() == null || param.getDirector().equals("")) {
            System.out.println("Director is null");
            return null;
        }
        return QMovie.movie.movieWorkers.any().workers.name.eq(param.getDirector().getName());
    }

    private static BooleanExpression movieActorContain(MovieSearchDTO param) {
        if (param.getActor() == null || param.getActor().equals("")) {
            System.out.println("Actor is null");
            return null;
        }
        return QMovie.movie.movieWorkers.any().workers.name.eq(param.getActor().getName());
    }

    // 5) 영화정보 조회3: 영화와 출연배우를 조회하되 영화에 대한 페이징(일대다 N+1과 페이징 동시 해결)
    // 영화는 2건 단위로 페이징
    static void findMovieWithActors(Integer page) {

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMovie qMovie = QMovie.movie;
        QMovieWorker qMovieWorker = QMovieWorker.movieWorker;
        Integer pagingSize = 2;

        Map<String, List<String>> results = query.from(qMovie, qMovieWorker)
                .leftJoin(qMovie.movieWorkers, qMovieWorker)
                .where(qMovie.id.eq(qMovieWorker.movie.id), qMovieWorker.roleType.isNotNull())
                .offset((page-1)*8).limit(pagingSize*4)
                .transform(groupBy(qMovie.name).as(list(qMovieWorker.workers.name)));
        results.entrySet().stream().forEach(entry-> {
            System.out.println(entry);
        });
    }

    // 6. 상영정보 확인
    static void ScreenInfo(Integer screenId){

        TypedQuery<Screen> query = em.createQuery("select distinct s from Screen s join fetch s.movie where s.id=:screenId",Screen.class);
        query.setParameter("screenId", screenId);
        Screen screen = query.getSingleResult();

        TypedQuery<Theater> query1 = em.createQuery("select distinct t from Theater t join fetch t.seats where t.id=:theaterId",Theater.class);
        query1.setParameter("theaterId",screen.getTheater().getId());
        Theater theater = query1.getSingleResult();

        Integer[] possible = {1,1,1,1,1,1,1,1,1,1};
        HashMap<Integer, Boolean> validSeats = new HashMap<>();

        for(Seat seat : theater.getSeats())
            validSeats.put(seat.getId(), seat.getState());

        Integer minKey = Collections.min(validSeats.keySet());

        //예약 가능한 좌석 보이기
        TypedQuery<Ticket> ticketTypedQuery = em.createQuery("select distinct t from Ticket t join fetch t.screenSeats where t.screen="+screenId,Ticket.class);
        //ticketTypedQuery.setParameter("screenId", screenId);
        List<Ticket> tickets = ticketTypedQuery.getResultList();

        for(Ticket ticket : tickets){
            for(ScreenSeat screenSeat : ticket.getScreenSeats()){
                validSeats.replace(screenSeat.getSeat().getId(), false);
            }
        }

        for(Integer seatId : validSeats.keySet()){
            if(validSeats.get(seatId).equals(false))
                possible[seatId - minKey] = 0;
        }

        System.out.println("영화 이름 → " + screen.getMovie().getName());
        System.out.println("시작 시간 → " + screen.getStartTime());
        System.out.println("종료 시간 → " + screen.getEndTime());

        System.out.println("총좌석 및 예매 가능 좌석↓");
        System.out.println("           Screen");
        //System.out.println("---------------Screen---------------");
        System.out.printf("     1    2    3    4    5");
        for(Integer i =0; i<10; i++)
        {
            if(i.equals(5) || i.equals(0))
            {
                System.out.println("");
                System.out.print((i/5)+1);
            }

            if(possible[i].equals(0))
                System.out.printf("    ■");
            else
                System.out.printf("    □");
        }
        System.out.println();
        System.out.println("■ : 예약 불가능, □ : 예약 가능 ");
    }

    // 7. 예매
    static void Ticketing(String userName, Integer screenId, Integer[] seatsId){

        //유저 찾기
        TypedQuery<User> userTypedQuery = em.createQuery("select u from User u where u.name=:findName", User.class);
        userTypedQuery.setParameter("findName",userName);
        User user = userTypedQuery.getSingleResult();

        //상영찾기
        TypedQuery<Screen> screenTypedQuery = em.createQuery("select s from Screen s where s.id=:screenId", Screen.class);
        screenTypedQuery.setParameter("screenId",screenId);
        Screen screen = screenTypedQuery.getSingleResult();

        //좌석찾기
        //Integer[] seatsId = {3, 4};
        Integer count = seatsId.length;
        Seat[] seats = new Seat[count];

        for(Integer i = 0; i < count; i++){
            TypedQuery<Seat> seatTypedQuery = em.createQuery("select s from Seat s where s.id=:seatId", Seat.class);
            seatTypedQuery.setParameter("seatId",seatsId[i]);
            seats[i] = seatTypedQuery.getSingleResult();
        }

        //예매 티켓 추가
        Ticket newTicket = new Ticket(user, screen);
        em.persist(newTicket);

        //예매 좌석 추가
        ScreenSeat[] screenSeats = new ScreenSeat[count];
        for (Integer i = 0; i < count; i++){
            screenSeats[i] = new ScreenSeat(newTicket, seats[i]);
            em.persist(screenSeats[i]);
        }
    }


    // 8. 예매 취소
    static void CancelTicketing(int ticketId){

        TypedQuery<Ticket> query = em.createQuery("select distinct t from Ticket t join fetch t.screenSeats where t.id=:ticketId", Ticket.class);
        query.setParameter("ticketId",ticketId);
        List<Ticket> result = query.getResultList();

        for(Ticket ticket : result) {
            ticket.setState(false);
            for(ScreenSeat screenSeat: ticket.getScreenSeats())
                em.remove(screenSeat);
        }
    }

    // 9.
    static void TicketInfo(int ticketId){

        TypedQuery<Ticket> ticketTypedQuery = em.createQuery("select distinct t from Ticket t join fetch t.screenSeats where t.id=:ticketId",Ticket.class); //원하는 예매 티켓 아이디를 이용하여 티켓 조회
        ticketTypedQuery.setParameter("ticketId",ticketId); //원하는 티켓 아이디를 파라미터 바인딩
        List<Ticket> tickets = ticketTypedQuery.getResultList(); //원하는 티켓 정보 받아오기
        if(tickets.isEmpty()) //만약 원하는 티켓의 정보가 없어서 아무것도 받아오지 않는다면 예매 정보가 없다고 출력
            System.out.println("예매 정보가 없습니다.");

        else { //원하는 티켓의 정보를 받아왔을 경우
            Ticket ticket = tickets.get(0);

            TypedQuery<Screen> query = em.createQuery("select distinct s from Screen s join fetch s.theater join fetch s.movie where s.id=:screenId", Screen.class); //예매한 영화와 상영하는 상영관에 대한 정보를 조회
            query.setParameter("screenId", ticket.getScreen().getId()); //상영 아이디를 이용하여 파라미터 바인딩
            Screen result = query.getSingleResult();//원하는 상영 정보 받아오기

            Seat[] seats = new Seat[ticket.getScreenSeats().size()]; //해당 티켓에 예매 되어있는 좌석의 수 만큼 좌석 클래스 생성
            Integer i = 0;

            ArrayList<String> seatNames = new ArrayList<>(); //좌석의 이름을 저장할 리스트

            for (ScreenSeat screenSeat : ticket.getScreenSeats()) { //예매 되어있는 좌석의 수 만큼 반복문
                TypedQuery<Seat> seatTypedQuery = em.createQuery("select distinct s from Seat s where s.id=:seatId", Seat.class); //예매된 좌석의 정보를 조회
                seatTypedQuery.setParameter("seatId", screenSeat.getSeat().getId()); //예매된 좌석의 아이디를 이용하여 파라미터 바인딩
                seats[i] = seatTypedQuery.getSingleResult(); // 원하는 좌석의 정보 받아오기

                seatNames.add("좌석 → " + seats[i].getSeatRow() + "행 " + seats[i].getSeatColumn() + "열 " +
                        +((seats[i].getSeatRow() - 1) * 5 + seats[i].getSeatColumn()) + "번"); //예매된 좌석의 이름을 리스트에 저장하기
                i++;
            }

            //예매된 티켓에 대한 정보 출력
            System.out.println("상영관 → " + result.getTheater().getName());
            System.out.println("영화 이름 → " + result.getMovie().getName());
            System.out.println("시작 시간 → " + result.getStartTime());
            System.out.println("종료 시간 → " + result.getEndTime());

            for (String seatName : seatNames)
                System.out.println("seatName = " + seatName);

            ScreenInfo(ticket.getScreen().getId());
        }
    }

    // 10) 영속성 전이를 이용한 삭제 -  사용자를 삭제할 경우, 해당 사용자와 연관된 예매내역 자동 삭제
    static void UserDeleteTicketDelete(Integer userId) {
        User deleteUser = em.find(User.class, userId);
        em.remove(deleteUser);
    }


    static Integer getScreenId(Movie movie, String theater){

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMovie qMovie = QMovie.movie;
        QTheater qTheater =QTheater.theater;
        QScreen qScreen = QScreen.screen;

        final Integer[] scId = new Integer[1];
        List<Screen> screenId = query.selectFrom(qScreen)
                .where(qMovie.name.eq(movie.getName()).and(qTheater.name.eq(theater)))
                .fetch();
        screenId.stream().forEach(m-> scId[0] = m.getId());

        return scId[0];
    }

    public static void main(String[] args) {
        try {
            tx.begin();

            // 초기 데이터
//            dataset();

            // 영화 정보
            Movie movie = new Movie();
            movie.setOpeningDate(format.parse("2021-12-24"));
            movie.setName("학교 종강의 날");
            movie.setId(3);

            Movie movie2 = new Movie();
            movie2.setOpeningDate(format.parse("2022-2-15"));
            movie2.setName("취업, 그놈이 도망간다");

            Director director = new Director();
            director.setName("봉준호");

            Actor actor = new Actor();
            actor.setName("장현영");

            User user = new User();
            user.setName("차채원");


            //1.user 추가
//            CreateUser(new User(), "차채원", 24, "구미시" , "대학로" , "39177");

            //2.user 정보 수정
//            EditUser("장현영", "장현일");

            // 기능3
//            showMovieWithWorkerId(movie.getId());

            // 기능4
//            findMovieWithWorkerOpeningDateRunningTime(null, null, null);

//            findMovieWithWorkerOpeningDateRunningTime(director, null, null);
//            findMovieWithWorkerOpeningDateRunningTime(null, actor, null);
//            findMovieWithWorkerOpeningDateRunningTime(null, null, movie.getOpeningDate());
//
            findMovieWithWorkerOpeningDateRunningTime(director, actor, null);
//            findMovieWithWorkerOpeningDateRunningTime(director, null, movie.getOpeningDate());
//            findMovieWithWorkerOpeningDateRunningTime(null, actor, movie.getOpeningDate());
//
//            findMovieWithWorkerOpeningDateRunningTime(director, actor, movie.getOpeningDate());

            // 기능 5
//            Integer pagingSize = 2;
//            findMovieWithActors(pagingSize);

            //6. 상영정보 확인
//            ScreenInfo(getScreenId(movie2, "상영관1"));

            // 7. 예매
/*            TicketInfo(76);
            Integer[] seatsId = {48,49};
            Ticketing("차채원", getScreenId(movie2, "상영관1"), seatsId);
            TicketInfo(76);*/

            // 8. 예매 취소
//            CancelTicketing(76);
//            TicketInfo(76);
//            ScreenInfo(getScreenId(movie2, "상영관1"));

            // 10. 유저 삭제
//            UserDeleteTicketDelete(75);

            em.flush();
            em.clear();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
