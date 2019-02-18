package com.fr.adaming.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The entity Travel is about the choices of customers It's linked to customers
 * and travel
 *
 *
 * @author Karguel
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Travel {
	/**
	 * @param Travel Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * @param number of nights
	 */
	@Column(nullable = false)
	private int nbrNight;

	/**
	 * @param destiantion
	 */
	@Column(nullable = false)
	private String destination;
	/**
	 * @param periodBegin
	 */
	@Column(nullable = false)
	private LocalDate periodBegin;
	/**
	 * @param periodEnd
	 */
	@Column(nullable = false)
	private LocalDate periodEnd;
	/**
	 * @param list of booking
	 */
	@JsonManagedReference
	@OneToMany(mappedBy = "travel", fetch = FetchType.LAZY)
	private List<Booking> lbooking;
	/**
	 * @param list of flight
	 */
	@JsonManagedReference
	@OneToMany(mappedBy = "travel", fetch = FetchType.LAZY)
	private List<Flight> lflight;
	/**
	 * @param hotel
	 */
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hotel")
	private Hotel lhotel;

	public Travel(int nbrNight, String destination, LocalDate periodBegin, LocalDate periodEnd, List<Booking> lbooking,
			List<Flight> lflight, Hotel lhotel) {
		super();
		this.nbrNight = nbrNight;
		this.destination = destination;
		this.periodBegin = periodBegin;
		this.periodEnd = periodEnd;
		this.lbooking = lbooking;
		this.lflight = lflight;
		this.lhotel = lhotel;
	}

}
