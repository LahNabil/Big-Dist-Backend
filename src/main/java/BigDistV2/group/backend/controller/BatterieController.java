package BigDistV2.group.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BigDistV2.group.backend.model.Batterie;
import BigDistV2.group.backend.repository.BatterieRepository;

@RestController
@RequestMapping("/batteries")
@CrossOrigin(origins = "http://localhost:5173/")

public class BatterieController {
	@Autowired
	private BatterieRepository batterieRepository;
	
	@GetMapping
	public List<Batterie> afficherBatteries(){
		return batterieRepository.findAll();
	}
	@PostMapping("/add")
	private Batterie AjouterBatterie(@RequestBody Batterie B) {
		return batterieRepository.save(B);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Batterie> getBatterieById(@PathVariable int id) {
	    Optional<Batterie> optionalBatterie = batterieRepository.findById(id);

	    if (optionalBatterie.isPresent()) {
	        Batterie batterie = optionalBatterie.get();
	        return ResponseEntity.ok(batterie);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/rechercher/{reference}")
	public List<Batterie> rechercherBatterieParNom(@PathVariable String reference) {
	    return batterieRepository.findByReference(reference);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteBatterie(@PathVariable int id) {
	    Optional<Batterie> optionalBatterie = batterieRepository.findById(id);

	    if (optionalBatterie.isPresent()) {
	        batterieRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	@PutMapping("/edit/{id}")
	public ResponseEntity<Batterie> editBatterie(@PathVariable int id, @RequestBody Batterie updatedBatterie) {
	    Optional<Batterie> optionalBatterie = batterieRepository.findById(id);

	    if (optionalBatterie.isPresent()) {
	        Batterie existingBatterie = optionalBatterie.get();
	        // Mettez à jour les propriétés de la batterie existante avec les nouvelles valeurs
	        existingBatterie.setMarque(updatedBatterie.getMarque());
	        existingBatterie.setReference(updatedBatterie.getReference());
	        existingBatterie.setPrix(updatedBatterie.getPrix());
	        existingBatterie.setGarantie(updatedBatterie.getGarantie());
	        // Enregistrez les modifications dans la base de données
	        batterieRepository.save(existingBatterie);
	        return ResponseEntity.ok(existingBatterie);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
