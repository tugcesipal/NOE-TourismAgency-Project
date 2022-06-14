package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.web.controller;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.logging.Logger;


@Controller
public class HotelListController {
  private final Logger LOG = Logger.getLogger(HotelListController.class.getSimpleName());
  private final HotelService hotelService;

  @Autowired
  public HotelListController(final HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping("/hotelliste")
  public String getHotels(
      final Model model,
      final @RequestParam("page") Optional<Integer> page,
      final @RequestParam("size") Optional<Integer> size) {
    LOG.info("GET Request for page: " + page + " and size: " + size);
    int currentPage = page.orElse(1);
    int pageSize = size.orElse(2);

    Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize);
    Page<Hotel> hotelsPage = hotelService.findAllOrderedById(pageRequest);
    model.addAttribute("hotelPage", hotelsPage);

    return "hotelliste";
  }

  @GetMapping(value = "/hotelliste/delete/{id}")
  public String delete(@PathVariable("id") int id) {
    LOG.info("DELETE Request for id:" + id);
    hotelService.delete(id);
    return "redirect:/hotelliste";
  }
}

