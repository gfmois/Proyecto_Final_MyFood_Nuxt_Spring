export default function ({ $fetch, req }: any) {
  $fetch.onRequest((config: any) => {
    const token = useCookie("token_client");

    if (req) {
      console.log("A");
      // Aquí puedes modificar las opciones de la solicitud, como los headers
      config.headers["Authorization"] = `Bearer ${token}`;
    }
  });
}
