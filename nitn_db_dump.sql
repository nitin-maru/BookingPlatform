--
-- PostgreSQL database dump
--

-- Dumped from database version 15.8 (Postgres.app)
-- Dumped by pg_dump version 15.8 (Postgres.app)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: booking; Type: TABLE; Schema: public; Owner: nitin
--

CREATE TABLE public.booking (
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    movie_id bigint NOT NULL,
    theatre_id bigint NOT NULL,
    screen_id bigint NOT NULL,
    show_time timestamp without time zone NOT NULL,
    total_tickets integer NOT NULL
);


ALTER TABLE public.booking OWNER TO nitin;

--
-- Name: booking_id_seq; Type: SEQUENCE; Schema: public; Owner: nitin
--

CREATE SEQUENCE public.booking_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.booking_id_seq OWNER TO nitin;

--
-- Name: booking_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nitin
--

ALTER SEQUENCE public.booking_id_seq OWNED BY public.booking.id;


--
-- Name: customer; Type: TABLE; Schema: public; Owner: nitin
--

CREATE TABLE public.customer (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    phone_number character varying(20) NOT NULL
);


ALTER TABLE public.customer OWNER TO nitin;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: nitin
--

CREATE SEQUENCE public.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_id_seq OWNER TO nitin;

--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nitin
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- Name: movie; Type: TABLE; Schema: public; Owner: nitin
--

CREATE TABLE public.movie (
    id bigint NOT NULL,
    title character varying(255),
    language character varying(50),
    genre character varying(50),
    description text,
    release_date timestamp without time zone
);


ALTER TABLE public.movie OWNER TO nitin;

--
-- Name: movie_id_seq; Type: SEQUENCE; Schema: public; Owner: nitin
--

CREATE SEQUENCE public.movie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movie_id_seq OWNER TO nitin;

--
-- Name: movie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nitin
--

ALTER SEQUENCE public.movie_id_seq OWNED BY public.movie.id;


--
-- Name: screen; Type: TABLE; Schema: public; Owner: nitin
--

CREATE TABLE public.screen (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    theatre_id bigint NOT NULL,
    seat_capacity integer NOT NULL
);


ALTER TABLE public.screen OWNER TO nitin;

--
-- Name: screen_id_seq; Type: SEQUENCE; Schema: public; Owner: nitin
--

CREATE SEQUENCE public.screen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.screen_id_seq OWNER TO nitin;

--
-- Name: screen_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nitin
--

ALTER SEQUENCE public.screen_id_seq OWNED BY public.screen.id;


--
-- Name: showtime; Type: TABLE; Schema: public; Owner: nitin
--

CREATE TABLE public.showtime (
    id bigint NOT NULL,
    movie_id bigint NOT NULL,
    screen_id bigint NOT NULL,
    show_time timestamp without time zone NOT NULL
);


ALTER TABLE public.showtime OWNER TO nitin;

--
-- Name: showtime_id_seq; Type: SEQUENCE; Schema: public; Owner: nitin
--

CREATE SEQUENCE public.showtime_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.showtime_id_seq OWNER TO nitin;

--
-- Name: showtime_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nitin
--

ALTER SEQUENCE public.showtime_id_seq OWNED BY public.showtime.id;


--
-- Name: theater; Type: TABLE; Schema: public; Owner: nitin
--

CREATE TABLE public.theater (
    id bigint NOT NULL,
    name character varying(255),
    location character varying(255)
);


ALTER TABLE public.theater OWNER TO nitin;

--
-- Name: theater_id_seq; Type: SEQUENCE; Schema: public; Owner: nitin
--

CREATE SEQUENCE public.theater_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.theater_id_seq OWNER TO nitin;

--
-- Name: theater_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nitin
--

ALTER SEQUENCE public.theater_id_seq OWNED BY public.theater.id;


--
-- Name: booking id; Type: DEFAULT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.booking ALTER COLUMN id SET DEFAULT nextval('public.booking_id_seq'::regclass);


--
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- Name: movie id; Type: DEFAULT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.movie ALTER COLUMN id SET DEFAULT nextval('public.movie_id_seq'::regclass);


--
-- Name: screen id; Type: DEFAULT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.screen ALTER COLUMN id SET DEFAULT nextval('public.screen_id_seq'::regclass);


--
-- Name: showtime id; Type: DEFAULT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.showtime ALTER COLUMN id SET DEFAULT nextval('public.showtime_id_seq'::regclass);


--
-- Name: theater id; Type: DEFAULT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.theater ALTER COLUMN id SET DEFAULT nextval('public.theater_id_seq'::regclass);


--
-- Data for Name: booking; Type: TABLE DATA; Schema: public; Owner: nitin
--

COPY public.booking (id, customer_id, movie_id, theatre_id, screen_id, show_time, total_tickets) FROM stdin;
1	1	1	1	1	2024-10-10 18:30:00	2
2	2	2	2	2	2024-10-11 20:00:00	4
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: nitin
--

COPY public.customer (id, name, email, phone_number) FROM stdin;
1	John Doe	john.doe@example.com	123-456-7890
2	Jane Smith	jane.smith@example.com	098-765-4321
\.


--
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: nitin
--

COPY public.movie (id, title, language, genre, description, release_date) FROM stdin;
1	Inception	English	Sci-Fi	A mind-bending thriller	2010-07-16 00:00:00
2	Interstellar	English	Sci-Fi	A space exploration journey	2014-11-07 00:00:00
3	The Dark Knight	English	Action	A superhero fights crime in Gotham	2008-07-18 00:00:00
\.


--
-- Data for Name: screen; Type: TABLE DATA; Schema: public; Owner: nitin
--

COPY public.screen (id, name, theatre_id, seat_capacity) FROM stdin;
1	Screen 1	1	150
2	Screen 2	1	200
3	Screen 1	2	180
4	Screen 2	2	220
5	Screen 1	3	300
\.


--
-- Data for Name: showtime; Type: TABLE DATA; Schema: public; Owner: nitin
--

COPY public.showtime (id, movie_id, screen_id, show_time) FROM stdin;
1	1	1	2024-10-10 18:30:00
2	1	1	2024-10-10 21:00:00
3	2	2	2024-10-11 20:00:00
4	3	3	2024-10-12 19:00:00
\.


--
-- Data for Name: theater; Type: TABLE DATA; Schema: public; Owner: nitin
--

COPY public.theater (id, name, location) FROM stdin;
1	PVR Cinemas	New York
2	Cineplex	Los Angeles
3	IMAX	San Francisco
\.


--
-- Name: booking_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nitin
--

SELECT pg_catalog.setval('public.booking_id_seq', 2, true);


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nitin
--

SELECT pg_catalog.setval('public.customer_id_seq', 2, true);


--
-- Name: movie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nitin
--

SELECT pg_catalog.setval('public.movie_id_seq', 3, true);


--
-- Name: screen_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nitin
--

SELECT pg_catalog.setval('public.screen_id_seq', 5, true);


--
-- Name: showtime_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nitin
--

SELECT pg_catalog.setval('public.showtime_id_seq', 4, true);


--
-- Name: theater_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nitin
--

SELECT pg_catalog.setval('public.theater_id_seq', 3, true);


--
-- Name: booking booking_pkey; Type: CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: movie movie_pkey; Type: CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (id);


--
-- Name: screen screen_pkey; Type: CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.screen
    ADD CONSTRAINT screen_pkey PRIMARY KEY (id);


--
-- Name: showtime showtime_pkey; Type: CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.showtime
    ADD CONSTRAINT showtime_pkey PRIMARY KEY (id);


--
-- Name: theater theater_pkey; Type: CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.theater
    ADD CONSTRAINT theater_pkey PRIMARY KEY (id);


--
-- Name: booking fk_customer; Type: FK CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- Name: booking fk_movie; Type: FK CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES public.movie(id);


--
-- Name: showtime fk_movie; Type: FK CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.showtime
    ADD CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES public.movie(id) ON DELETE CASCADE;


--
-- Name: booking fk_screen; Type: FK CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fk_screen FOREIGN KEY (screen_id) REFERENCES public.screen(id);


--
-- Name: showtime fk_screen; Type: FK CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.showtime
    ADD CONSTRAINT fk_screen FOREIGN KEY (screen_id) REFERENCES public.screen(id) ON DELETE CASCADE;


--
-- Name: screen fk_theatre; Type: FK CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.screen
    ADD CONSTRAINT fk_theatre FOREIGN KEY (theatre_id) REFERENCES public.theater(id);


--
-- Name: booking fk_theatre; Type: FK CONSTRAINT; Schema: public; Owner: nitin
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fk_theatre FOREIGN KEY (theatre_id) REFERENCES public.theater(id);


--
-- PostgreSQL database dump complete
--

