Table tutores {
  id UUID [primary key]
  nome varchar
  cpf varchar
  telefone varchar
  endereco_id integer
}
Ref: tutores.endereco_id - enderecos.id

Table veterinarios {
  id UUID [primary key]
  especialidade varchar
  crmv long
  uf varchar
}
Ref: veterinarios.id - usuarios.id

Table usuarios {
  id UUID [primary key]
  email varchar
  senha varchar
  nome varchar
  cpf varchar
  cargo_id UUID
}
Ref: usuarios.cargo_id - cargos.id

Table cargos {
  id int [primary key]
  nome varchar
}

Table pets {
  id UUID [primary key]
  nome varchar
  peso double
  sexo varchar
  raca varchar
  especie varchar
  data_de_nascimento date
  tutor_id UUID
}
Ref: pets.tutor_id > tutores.id

Table consultas {
  id UUID [primary key]
  data_realizacao datetime
  observacoes varchar
  pet_id UUID
  tipo_consulta_id integer
  // pagamento_id UUID
}
Ref: consultas.pet_id > pets.id

Table tipos_consulta {
  id integer [primary key]
  nome varchar
  descricao varchar
  preco_base double
}
Ref: tipos_consulta.id < consultas.tipo_consulta_id

Table enderecos {
  id integer [primary key]
  bairro varchar
  numero long
  rua varchar
}

Table consulta_veterinario {
  consulta_id UUID [primary key]
  veterinario_id UUID [primary key]
}
Ref: consulta_veterinario.consulta_id > consultas.id
Ref: consulta_veterinario.veterinario_id > veterinarios.id

Table exames {
  id UUID [primary key]
  data_realizacao datetime
  resultado varchar
  tipo_exame_id UUID
  consulta_id integer
}
Ref: tipos_exame.id < exames.tipo_exame_id
Ref: consultas.id < exames.consulta_id

Table tipos_exame {
  id integer [primary key]
  nome varchar
  descricao varchar
  preco_base double
}

Table prescricoes {
  id UUID [primary key]
  medicamento varchar
  posologia varchar
  consulta_id integer
}
Ref: consultas.id < prescricoes.consulta_id

// Table pagamentos {
//   id UUID [primary key]
//   desconto double
//   preco_base double
//   preco_final double
//   servico_id UUID
// }
// Ref: pagamentos.servico_id > servicos.id
// Ref: pagamentos.id - consultas.pagamento_id

