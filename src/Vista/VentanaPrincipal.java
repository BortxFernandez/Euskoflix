package Vista;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	JButton btnVerPeliculas;
	JButton btnVerValoraciones;
	JButton btnVerTags;
	JButton btnObtenerAfines;
	private JTextField txtIntroduce;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVerPeliculas = new JButton("Ver peliculas");
		btnVerPeliculas.setBounds(47, 38, 165, 25);
		contentPane.add(btnVerPeliculas);
		
		btnVerValoraciones = new JButton("Ver valoraciones");
		btnVerValoraciones.setBounds(47, 76, 165, 25);
		contentPane.add(btnVerValoraciones);
		
		btnVerTags = new JButton("Ver tags");
		btnVerTags.setBounds(47, 114, 165, 25);
		contentPane.add(btnVerTags);
		
		btnObtenerAfines = new JButton("Obtener afines");
		btnObtenerAfines.setBounds(272, 196, 148, 25);
		contentPane.add(btnObtenerAfines);
		
		txtIntroduce = new JTextField();
		txtIntroduce.setText("Introduce un ID de usuario.");
		txtIntroduce.setBounds(47, 197, 188, 22);
		contentPane.add(txtIntroduce);
		txtIntroduce.setColumns(10);
	}
	
	//Listeners
	
		public void addVerPeliculasListener(ActionListener listenForBtnVerPeliculas) {
	        btnVerPeliculas.addActionListener(listenForBtnVerPeliculas);
	    }
		
		public void addVerValoracionesListener(ActionListener listenForBtnVerValoraciones) {
	        btnVerValoraciones.addActionListener(listenForBtnVerValoraciones);
	    }
		
		public void addVerTagsListener(ActionListener listenForBtnVerTags) {
	        btnVerTags.addActionListener(listenForBtnVerTags);
	    }
		
		public void addObtenerAfinesListener(ActionListener listenForBtnObtenerAfines) {
			btnObtenerAfines.addActionListener(listenForBtnObtenerAfines);
		}
		
		public String getTxtIntroduce() {
	        return this.txtIntroduce.getText();
	    }
}
