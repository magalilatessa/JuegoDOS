package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controlador.Controlador;
import Modelo.Ijugador;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;

public class VistaGrafica implements Ivista{

	private JFrame frame;
	private Controlador controlador;
	private JTextField TextoAgregarJugador;

	/**
	 * Create the application.
	 */
	public VistaGrafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 182, 193));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 182, 193));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("JUEGO DOS");
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 182, 193));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JButton BotonAgregarJugador = new JButton("Agregar jugador");
		BotonAgregarJugador.setBounds(113, 5, 111, 23);
		BotonAgregarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.agregarJugador(TextoAgregarJugador.getText());
				
			}
		});
		panel_1.setLayout(null);
		BotonAgregarJugador.setBackground(new Color(220, 20, 60));
		panel_1.add(BotonAgregarJugador);
		
		TextoAgregarJugador = new JTextField();
		TextoAgregarJugador.setBounds(229, 6, 86, 20);
		panel_1.add(TextoAgregarJugador);
		TextoAgregarJugador.setColumns(10);
		
		JList listJugadores = new JList();
		listJugadores.setSelectedIndex(0);
		listJugadores.setBackground(new Color(205, 133, 63));
		listJugadores.setBounds(320, 147, 79, -131);
		listJugadores.setToolTipText("asdf\r\nasdfgb\r\nzsdfgvb");
		panel_1.add(listJugadores);
		
		DefaultListModel<String> modelo=new DefaultListModel<>();
		listJugadores.setModel(modelo);
		modelo.addElement(this.TextoAgregarJugador.getText().trim());        
        this.TextoAgregarJugador.setText(""); 
		
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
		
	}

	@Override
	public void iniciar() {
		this.frame.setVisible(true);
		
	}
	
	public void mostrarJugadores(ArrayList<Ijugador> jugadores) {
		for (Ijugador jugador:jugadores) {
			
	
		}
	}
}
