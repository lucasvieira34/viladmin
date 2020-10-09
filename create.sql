create table contas (id_contas int8 generated by default as identity, nome varchar(255) not null, observacao varchar(255), pagamento date, status varchar(255) not null, id_residencia int8, primary key (id_contas))
create table morador (id_morador int8 generated by default as identity, nome varchar(255) not null, id_usuario int8, primary key (id_morador))
create table residencia (id_residencia int8 generated by default as identity, casa varchar(255) not null, id_morador int8, primary key (id_residencia))
create table role (id_role int8 generated by default as identity, nome varchar(255), primary key (id_role))
create table usuario (id_usuario int8 generated by default as identity, login varchar(255) not null, senha varchar(255) not null, primary key (id_usuario))
create table usuario_roles (id_usuario int8 not null, id_role int8 not null)
alter table if exists morador add constraint UK_ejhuoasxmclcjs3fp32sixr8b unique (id_usuario)
alter table if exists residencia add constraint UK_m6wxpsq0ojw3a6ryqvecyk20a unique (casa)
alter table if exists residencia add constraint UK_669j57tujmmp6ekrav1db3ug unique (id_morador)
alter table if exists role add constraint UK_psbnsrja0jvuncak7b0sqo2fi unique (nome)
alter table if exists contas add constraint FKdymeeja8a6manjgmha72nolfk foreign key (id_residencia) references residencia
alter table if exists morador add constraint FK56q7xfnyjj342l49waw0nm9st foreign key (id_usuario) references usuario
alter table if exists residencia add constraint FK5qc28gnf7tx6uxvoolpe89187 foreign key (id_morador) references morador
alter table if exists usuario_roles add constraint FK478qg359ptrhmrpw0ijep6qh foreign key (id_role) references role
alter table if exists usuario_roles add constraint FKo29oupngknrqv049pvyvqb4kp foreign key (id_usuario) references usuario