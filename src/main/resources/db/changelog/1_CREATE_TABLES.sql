CREATE TABLE "public"."pais" (
    "id" bigint NOT NULL,
    "nome" character varying(150) NOT NULL,
    CONSTRAINT "pais_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
------------------------------------------------------------------------------------------------
CREATE TABLE "public"."estado" (
    "id" bigint NOT NULL,
    "nome" character varying(100) NOT NULL,
    "uf" character varying(2) NOT NULL,
    "id_pais" bigint NOT NULL,
    CONSTRAINT "estado_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "estado_pais_fkey" FOREIGN KEY (id_pais) REFERENCES pais(id) NOT DEFERRABLE
) WITH (oids = false);
------------------------------------------------------------------------------------------------
CREATE TABLE "public"."cliente" (
    "id" bigint NOT NULL,
    "cpf" character varying(11) NOT NULL,
    "data_nascimento" timestamp NOT NULL,
    "email" character varying(50) NOT NULL,
    "nome" character varying(150) NOT NULL,
    "situacao" character varying(255) NOT NULL,
    "id_estado" bigint,
    CONSTRAINT "cliente_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "cliente_email_unique" UNIQUE ("email"),
    CONSTRAINT "cliente_cpf_unique" UNIQUE ("cpf"),
    CONSTRAINT "cliente_estado_fkey" FOREIGN KEY (id_estado) REFERENCES estado(id) NOT DEFERRABLE
) WITH (oids = false);
------------------------------------------------------------------------------------------------