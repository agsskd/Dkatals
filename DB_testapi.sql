--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2021-12-22 20:49:31

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'LATIN8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16540)
-- Name: test_transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test_transactions (
    transaction_id integer NOT NULL,
    user_id integer NOT NULL,
    description character varying NOT NULL,
    amount integer NOT NULL
);


ALTER TABLE public.test_transactions OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16539)
-- Name: auto_increment_test; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auto_increment_test
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auto_increment_test OWNER TO postgres;

--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 216
-- Name: auto_increment_test; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auto_increment_test OWNED BY public.test_transactions.transaction_id;


--
-- TOC entry 210 (class 1259 OID 16428)
-- Name: et_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.et_categories (
    category_id integer NOT NULL,
    user_id integer NOT NULL,
    title character varying(20) NOT NULL,
    description character varying(50) NOT NULL
);


ALTER TABLE public.et_categories OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16454)
-- Name: et_categories_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.et_categories_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.et_categories_seq OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16438)
-- Name: et_transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.et_transactions (
    transaction_id integer NOT NULL,
    category_id integer NOT NULL,
    user_id integer NOT NULL,
    amount numeric(10,2) NOT NULL,
    note character varying(50) NOT NULL,
    transaction_date bigint NOT NULL
);


ALTER TABLE public.et_transactions OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16455)
-- Name: et_transactions_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.et_transactions_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.et_transactions_seq OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16421)
-- Name: et_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.et_users (
    user_id integer NOT NULL,
    first_name character varying(20) NOT NULL,
    last_name character varying(20) NOT NULL,
    email character varying(30) NOT NULL,
    password text NOT NULL
);


ALTER TABLE public.et_users OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16453)
-- Name: et_users_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.et_users_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.et_users_seq OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16534)
-- Name: test; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test (
    test_id integer NOT NULL,
    description character varying(50) NOT NULL,
    date character varying(8) NOT NULL
);


ALTER TABLE public.test OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16547)
-- Name: test_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test_users (
    user_id integer NOT NULL,
    username character varying(20) NOT NULL,
    password character varying NOT NULL
);


ALTER TABLE public.test_users OWNER TO postgres;

--
-- TOC entry 3345 (class 0 OID 16428)
-- Dependencies: 210
-- Data for Name: et_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.et_categories (category_id, user_id, title, description) VALUES (1, 2, 'Shopping', 'update categories');


--
-- TOC entry 3346 (class 0 OID 16438)
-- Dependencies: 211
-- Data for Name: et_transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.et_transactions (transaction_id, category_id, user_id, amount, note, transaction_date) VALUES (1000, 1, 2, 70000.00, 'test add transaction rest api', 13122110000000);


--
-- TOC entry 3344 (class 0 OID 16421)
-- Dependencies: 209
-- Data for Name: et_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.et_users (user_id, first_name, last_name, email, password) VALUES (2, 'Ryan', 'Pratama', 'ryan@gmail.com', '$2a$10$QniXGHJllcIroHKZ7RNuw.eiTWj6m5G2YFs6jOlTDMQSRXjXynrV2');


--
-- TOC entry 3350 (class 0 OID 16534)
-- Dependencies: 215
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.test (test_id, description, date) VALUES (1, 'Data Test Rest-1', '20211217');
INSERT INTO public.test (test_id, description, date) VALUES (2, 'Data Test Rest-2', '20211218');
INSERT INTO public.test (test_id, description, date) VALUES (3, 'Data Test Rest-3', '20211218');
INSERT INTO public.test (test_id, description, date) VALUES (4, 'Data Test Rest-4', '20211218');


--
-- TOC entry 3352 (class 0 OID 16540)
-- Dependencies: 217
-- Data for Name: test_transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.test_transactions (transaction_id, user_id, description, amount) VALUES (1, 1, 'deposit', 100);


--
-- TOC entry 3353 (class 0 OID 16547)
-- Dependencies: 218
-- Data for Name: test_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.test_users (user_id, username, password) VALUES (1, 'Alice', '$2a$10$QniXGHJllcIroHKZ7RNuw.eiTWj6m5G2YFs6jOlTDMQSRXjXynrV2');


--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 216
-- Name: auto_increment_test; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auto_increment_test', 5, true);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 213
-- Name: et_categories_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.et_categories_seq', 19, true);


--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 214
-- Name: et_transactions_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.et_transactions_seq', 1000, true);


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 212
-- Name: et_users_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.et_users_seq', 2, true);


--
-- TOC entry 3192 (class 2606 OID 16432)
-- Name: et_categories et_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.et_categories
    ADD CONSTRAINT et_categories_pkey PRIMARY KEY (category_id);


--
-- TOC entry 3194 (class 2606 OID 16442)
-- Name: et_transactions et_transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.et_transactions
    ADD CONSTRAINT et_transactions_pkey PRIMARY KEY (transaction_id);


--
-- TOC entry 3190 (class 2606 OID 16427)
-- Name: et_users et_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.et_users
    ADD CONSTRAINT et_users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3196 (class 2606 OID 16538)
-- Name: test test_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_pkey PRIMARY KEY (test_id);


--
-- TOC entry 3198 (class 2606 OID 16546)
-- Name: test_transactions test_transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_transactions
    ADD CONSTRAINT test_transactions_pkey PRIMARY KEY (transaction_id);


--
-- TOC entry 3200 (class 2606 OID 16551)
-- Name: test_users test_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_users
    ADD CONSTRAINT test_users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3201 (class 2606 OID 16433)
-- Name: et_categories cat_users_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.et_categories
    ADD CONSTRAINT cat_users_fk FOREIGN KEY (user_id) REFERENCES public.et_users(user_id);


--
-- TOC entry 3204 (class 2606 OID 16552)
-- Name: test_transactions test_users_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_transactions
    ADD CONSTRAINT test_users_fk FOREIGN KEY (user_id) REFERENCES public.test_users(user_id) NOT VALID;


--
-- TOC entry 3202 (class 2606 OID 16443)
-- Name: et_transactions trans_cat_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.et_transactions
    ADD CONSTRAINT trans_cat_fk FOREIGN KEY (category_id) REFERENCES public.et_categories(category_id);


--
-- TOC entry 3203 (class 2606 OID 16448)
-- Name: et_transactions trans_users_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.et_transactions
    ADD CONSTRAINT trans_users_fk FOREIGN KEY (user_id) REFERENCES public.et_users(user_id);


--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE test_transactions; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.test_transactions TO ryan;


--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 216
-- Name: SEQUENCE auto_increment_test; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.auto_increment_test TO ryan;


--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE et_categories; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.et_categories TO ryan;


--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 213
-- Name: SEQUENCE et_categories_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.et_categories_seq TO ryan;


--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE et_transactions; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.et_transactions TO ryan;


--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 214
-- Name: SEQUENCE et_transactions_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.et_transactions_seq TO ryan;


--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE et_users; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.et_users TO ryan;


--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 212
-- Name: SEQUENCE et_users_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.et_users_seq TO ryan;


--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE test; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.test TO ryan;


--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE test_users; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.test_users TO ryan;


--
-- TOC entry 2049 (class 826 OID 16398)
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON SEQUENCES  TO ryan;


--
-- TOC entry 2048 (class 826 OID 16397)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES  TO ryan;


-- Completed on 2021-12-22 20:49:32

--
-- PostgreSQL database dump complete
--

