package Vista;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JFrame;

import Controlador.Controlador;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class VistaGrafica implements Ivista{

	private JFrame frame;
	private Controlador controlador;
	
	
	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaGrafica window = new VistaGrafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel carta = new JLabel("New label");
		Dimension d=new Dimension();
		d.height=2;
		d.width=4;
		carta.setIcon(new ImageIcon("C:\\Users\\maga_\\OneDrive\\Documentos\\Universidad\\POO\\CartasDOS\\1Azul.png"));
		carta.setSize(d);
		panel.add(carta);
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		
	}

	@Override
	public void iniciar() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
