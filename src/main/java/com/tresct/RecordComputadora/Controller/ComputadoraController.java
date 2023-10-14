package com.tresct.RecordComputadora.Controller;

import com.tresct.RecordComputadora.DiscoRecord;
import com.tresct.RecordComputadora.Model.ComputadoraRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class ComputadoraController {
    List<ComputadoraRecord> lista = new ArrayList<>();






    public ComputadoraController() {

            DiscoRecord discoWestern = new DiscoRecord("Western Digital",1,64);
            DiscoRecord discoSeagate = new DiscoRecord("Seagate",8,256);

            lista.add(new ComputadoraRecord("Intel NUC 12 Extreme",
                    8, discoWestern));
            lista.add(new ComputadoraRecord("Lenovo",
                    16, discoSeagate));
            lista.add(new ComputadoraRecord("HP",
                    32, discoWestern));
            lista.add(new ComputadoraRecord("Acer",
                    64, discoSeagate));

        }

    @GetMapping("v1/computadoras")
    public String computadoras(){

        return "[LISTA DE COMPUTADORAS]";

    }
    @GetMapping("v2/computadoras")
    public List<ComputadoraRecord> computadorasLista(){



        return lista;

     //   return computadoraModelo.lista;

    }

	@GetMapping("v2/computadoras/{marca}")
	public List<DiscoRecord> computadorasParametro(@PathVariable String marca) {


		return lista.stream()
				.filter(computadoraRecord -> computadoraRecord.marca().equals(marca))
				.map(computadoraRecord -> computadoraRecord.disco())
				.collect(Collectors.toList());
	}


    @GetMapping("v3/computadoras")
    public List<ComputadoraRecord> procesar() {
        List<ComputadoraRecord> ejemplo = new ArrayList<>();
        DiscoRecord discoWestern = new DiscoRecord("Western Digital",1,64);
        DiscoRecord discoSeagate = new DiscoRecord("Seagate",8,256);

     //   ejemplo.add()
       return Arrays.asList(new ComputadoraRecord("Lenovo", 16, discoWestern), new ComputadoraRecord("HP",32, discoSeagate));

    }

    @PostMapping("v2/computadoras")
    public String agregaNuevaComputadora(@RequestBody BodyRecord recordCuerpo) {

        DiscoRecord discoSeagate = new DiscoRecord("Seagate",8,256);
        String marcaEnviada = recordCuerpo.marca();
        int ramEnviada = recordCuerpo.ram();

        ComputadoraRecord temp = new ComputadoraRecord(
                marcaEnviada,ramEnviada,discoSeagate);

        lista.add(temp);

        return "ok";

    }

}
