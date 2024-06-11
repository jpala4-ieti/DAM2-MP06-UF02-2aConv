package com.project.juny2024;

import java.util.Collection;

public class Main {

   public static void main(String[] args) {

      Manager.createSessionFactory("juny2024/hibernate.properties", "juny2024/hibernate.cfg.xml");

      // Crear departaments
      Departament departament1 = Manager.addDepartament("Departament de Tecnologia");
      Departament departament2 = Manager.addDepartament("Departament de Recursos Humans");

      // Crear empleats per al Departament de Tecnologia
      Empleat empleat1 = Manager.addEmpleatToDepartament(departament1.getId(), "Anna Garcia");
      Empleat empleat2 = Manager.addEmpleatToDepartament(departament1.getId(), "Pere Martinez");

      // Crear empleats per al Departament de Recursos Humans
      Empleat empleat3 = Manager.addEmpleatToDepartament(departament2.getId(), "Maria Lopez");
      Empleat empleat4 = Manager.addEmpleatToDepartament(departament2.getId(), "Joan Sanchez");

      // Crear projectes i assignar empleats
      Projecte projecte1 = Manager.addProjecte("Projecte A", "2024-01-01", "2024-06-01");
      Manager.assignEmpleatToProjecte(projecte1.getId(), empleat1);
      Manager.assignEmpleatToProjecte(projecte1.getId(), empleat3);

      Projecte projecte2 = Manager.addProjecte("Projecte B", "2024-02-01", "2024-07-01");
      Manager.assignEmpleatToProjecte(projecte2.getId(), empleat2);
      Manager.assignEmpleatToProjecte(projecte2.getId(), empleat4);

      Projecte projecte3 = Manager.addProjecte("Projecte C", "2024-03-01", "2024-08-01");
      Manager.assignEmpleatToProjecte(projecte3.getId(), empleat1);
      Manager.assignEmpleatToProjecte(projecte3.getId(), empleat4);

      Projecte projecte4 = Manager.addProjecte("Projecte D", "2024-04-01", "2024-09-01");
      Manager.assignEmpleatToProjecte(projecte4.getId(), empleat2);
      Manager.assignEmpleatToProjecte(projecte4.getId(), empleat3);

      System.out.println("Departaments:");
      Collection<?> llista = Manager.listCollection(Departament.class);
      System.out.println(Manager.collectionToString(Departament.class, llista));

      System.out.println("Empleats:");
      llista = Manager.listCollection(Empleat.class);
      System.out.println(Manager.collectionToString(Empleat.class, llista));

      System.out.println("Projectes:");
      llista = Manager.listCollection(Projecte.class);
      System.out.println(Manager.collectionToString(Projecte.class, llista));

      Manager.close();
   }
}
