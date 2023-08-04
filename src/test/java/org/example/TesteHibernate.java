package org.example;

import dao.DaoGeneric;
import model.UsuarioPessoa;
import org.junit.Test;

public class TesteHibernate {

    @Test
    public void testeHibernateUtil(){
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

        UsuarioPessoa pessoa = new UsuarioPessoa();

        pessoa.setIdade(32);
        pessoa.setLogin("teste");
        pessoa.setNome("Rafael 2");
        pessoa.setSobrenome("Monteiro");
        pessoa.setEmail("teste@123.com");
        pessoa.setSenha("123");

        daoGeneric.salvar(pessoa);

    }

    @Test
    public void testeBuscar(){
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = new UsuarioPessoa();
        pessoa.setId(1L);

        pessoa = daoGeneric.pesquisar(pessoa);

        System.out.println(pessoa);

    }

    @Test
    public void testeBuscar2(){
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

        UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

        System.out.println(pessoa);

    }

    @Test
    public void testeUpdate(){
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

        pessoa.setIdade(99);
        pessoa.setNome("Nome atualizado Hibernate");
        pessoa.setSenha("ashkjashjaskasjasshakashjasksj");

        pessoa = daoGeneric.updateMerge(pessoa);

        System.out.println(pessoa);

    }


}
